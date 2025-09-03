package com.example.Surf.Controllers;

import com.example.Surf.DTO.UserDTO;
import com.example.Surf.Models.Zone;
import com.example.Surf.Services.UserService;
import com.example.Surf.Services.ZoneService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/users/update-location")
    public ResponseEntity<UserDTO> updateUserLocation(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUserLocation(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users/location/{deviceId}")
    public ResponseEntity<UserDTO> getUserLocation(@PathVariable String deviceId) {
        return userService.getUserLocation(deviceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserDTO>> getActiveUsers() {
        List<UserDTO> activeUsers = userService.getActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }

    @PostMapping("/users/check-location")
    public ResponseEntity<Map<String, Object>> checkUserLocation(@RequestBody UserDTO userDTO) {
        GeometryFactory factory = new GeometryFactory();
        Map<String, Object> response = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println("Checking user location: latitude=" + userDTO.getLatitude() + ", longitude=" + userDTO.getLongitude());

            List<Zone> zones = zoneService.findAllZones();
            System.out.println("Found " + zones.size() + " zones");

            for (Zone zone : zones) {
                System.out.println("Processing zone: ID=" + zone.getId() + ", Name=" + zone.getName() + ", Type=" + zone.getType());

                String coordinatesJson = zone.getCoordinates();
                System.out.println("Zone coordinates JSON: " + coordinatesJson);

                Map<String, Object> geoJson = mapper.readValue(coordinatesJson, Map.class);
                List<List<List<Double>>> coordinates = (List<List<List<Double>>>) geoJson.get("coordinates");
                if (coordinates == null || coordinates.isEmpty()) {
                    System.out.println("Invalid GeoJSON: No coordinates found for zone ID=" + zone.getId());
                    continue;
                }

                List<List<Double>> outerRing = coordinates.get(0);
                Coordinate[] coords = new Coordinate[outerRing.size()];
                for (int i = 0; i < outerRing.size(); i++) {
                    List<Double> point = outerRing.get(i);
                    if (point.size() < 2) {
                        System.out.println("Invalid coordinate at index " + i + ": " + point);
                        continue;
                    }
                    coords[i] = new Coordinate(point.get(0), point.get(1));
                    System.out.println("Parsed coordinate: [" + coords[i].x + ", " + coords[i].y + "]");
                }

                if (coords.length > 0 && !coords[0].equals(coords[coords.length - 1])) {
                    Coordinate[] closedCoords = new Coordinate[coords.length + 1];
                    System.arraycopy(coords, 0, closedCoords, 0, coords.length);
                    closedCoords[coords.length] = coords[0];
                    coords = closedCoords;
                    System.out.println("Closed polygon by adding first coordinate to end");
                }

                if (coords.length < 4) {
                    System.out.println("Invalid polygon: Fewer than 4 coordinates for zone ID=" + zone.getId());
                    continue;
                }

                Polygon polygon = factory.createPolygon(factory.createLinearRing(coords), null);
                Point point = factory.createPoint(new Coordinate(userDTO.getLongitude(), userDTO.getLatitude()));
                System.out.println("Checking if point [" + userDTO.getLongitude() + ", " + userDTO.getLatitude() + "] is inside polygon");

                if (polygon.contains(point)) {
                    System.out.println("User is inside zone: ID=" + zone.getId() + ", Name=" + zone.getName());
                    response.put("inside", true);
                    response.put("zoneId", zone.getId());
                    response.put("zoneType", mapZoneTypeToFrontend(zone.getType()));
                    response.put("beachId", zone.getBeach().getId());
                    response.put("beachName", zone.getBeach().getName());
                    return ResponseEntity.ok(response);
                } else {
                    System.out.println("User is not inside zone: ID=" + zone.getId());
                }
            }

            System.out.println("User is not inside any known zone");
            response.put("inside", false);
            response.put("zoneId", null);
            response.put("zoneType", "unknown");
            response.put("beachId", null);
            response.put("beachName", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error checking user location: " + e.getMessage());
            e.printStackTrace();
            response.put("inside", false);
            response.put("zoneId", null);
            response.put("zoneType", "unknown");
            response.put("beachId", null);
            response.put("beachName", null);
            return ResponseEntity.ok(response);
        }
    }

    private String mapZoneTypeToFrontend(String backendType) {
        switch (backendType.toUpperCase()) {
            case "BATHING":
                return "safe";
            case "DANGER":
                return "danger";
            case "SPORTS":
                return "sports";
            default:
                return "unknown";
        }
    }
}