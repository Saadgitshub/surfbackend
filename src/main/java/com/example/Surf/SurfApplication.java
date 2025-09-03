package com.example.Surf;

import com.example.Surf.Models.*;
import com.example.Surf.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SurfApplication {

    @Autowired
    private BeachRepository beachRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private SafetyInfoRepository safetyInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            System.out.println("Starting database initialization...");

            // Check if beaches exist to avoid resetting the database
            if (beachRepository.count() == 0) {
                System.out.println("No beaches found. Initializing beaches and zones...");

                // Clear existing data only if initializing
                beachRepository.deleteAll();
                zoneRepository.deleteAll();
                System.out.println("Cleared existing beach and zone data.");

                // Essaouira Beach
                Beach essaouiraBeach = new Beach();
                essaouiraBeach.setName("Essaouira Beach");
                essaouiraBeach.setCity("Essaouira");
                essaouiraBeach.setDescription("Main beach in Essaouira, known for kitesurfing and camel rides");
                beachRepository.save(essaouiraBeach);
                System.out.println("Saved Essaouira Beach: ID=" + essaouiraBeach.getId());

                // Essaouira Bathing Zone
                Zone essaouiraBathingZone = new Zone();
                essaouiraBathingZone.setName("Bathing Zone");
                essaouiraBathingZone.setType("BATHING");
                essaouiraBathingZone.setColor("blue");
                essaouiraBathingZone.setCoordinates("{\"type\":\"Polygon\",\"coordinates\":[[[-9.771646074801453,31.509528647196632],[-9.770221206763892,31.509207088319897],[-9.769026832084563,31.508706883424423],[-9.768041636021536,31.508132099675095],[-9.767371109886426,31.50738178269853],[-9.76667962980909,31.50650640527971],[-9.766176735207125,31.505345177686863],[-9.765673840606041,31.504076743369808],[-9.76531762359582,31.502718963621902],[-9.763934663441091,31.502951216819397],[-9.764144202857949,31.504362589226048],[-9.764374696217061,31.505613154566078],[-9.764877590818998,31.50645281047835],[-9.766134827323441,31.507560430145034],[-9.767119662584207,31.508542984991465],[-9.768230221496964,31.509489800799003],[-9.769110287049728,31.51006145852992],[-9.770786602387858,31.51029369348373],[-9.771289496989823,31.51024010085324],[-9.771708575824334,31.509525529509787],[-9.771646074801453,31.509528647196632]]]}");
                essaouiraBathingZone.setRules(Arrays.asList("No surfing", "Stay within flags"));
                essaouiraBathingZone.setBeach(essaouiraBeach);
                zoneRepository.save(essaouiraBathingZone);
                System.out.println("Saved Essaouira Bathing Zone: ID=" + essaouiraBathingZone.getId());

                // Essaouira Kitesurf Zone
                Zone essaouiraKitesurfZone = new Zone();
                essaouiraKitesurfZone.setName("Kitesurf Zone");
                essaouiraKitesurfZone.setType("SPORTS");
                essaouiraKitesurfZone.setColor("red");
                essaouiraKitesurfZone.setCoordinates("{\"type\":\"Polygon\",\"coordinates\":[[[-9.76530259215059,31.502354413159296],[-9.765329076910092,31.499757515937958],[-9.765699863548377,31.498199342982375],[-9.767103555822018,31.496099155741433],[-9.768268885256958,31.494179588510534],[-9.770361181287086,31.49314074745979],[-9.77165893452164,31.492350317191807],[-9.772903718236165,31.49126628767513],[-9.771844327840256,31.489775726574138],[-9.769063428053613,31.489256283033782],[-9.767712705298976,31.491085614867316],[-9.766229558745806,31.49286974354915],[-9.764799381712692,31.494902253739156],[-9.763581082757696,31.496889554324284],[-9.76276005520154,31.49889939491375],[-9.763157326600378,31.501022100986503],[-9.763660537037225,31.502715714882996],[-9.76530259215059,31.502354413159296]]]}");
                essaouiraKitesurfZone.setRules(Arrays.asList("No swimming", "Kitesurf only"));
                essaouiraKitesurfZone.setBeach(essaouiraBeach);
                zoneRepository.save(essaouiraKitesurfZone);
                System.out.println("Saved Essaouira Kitesurf Zone: ID=" + essaouiraKitesurfZone.getId());

                essaouiraBeach.setZones(Arrays.asList(essaouiraBathingZone, essaouiraKitesurfZone));
                beachRepository.save(essaouiraBeach);
                System.out.println("Updated Essaouira Beach with zones: ID=" + essaouiraBeach.getId());

                // Safi Beach
                Beach safiBeach = new Beach();
                safiBeach.setName("Safi Beach");
                safiBeach.setCity("Safi");
                safiBeach.setDescription("Popular beach in Safi, known for surfing and scenic views");
                beachRepository.save(safiBeach);
                System.out.println("Saved Safi Beach: ID=" + safiBeach.getId());

                // Safi Bathing Zone
                Zone safiBathingZone = new Zone();
                safiBathingZone.setName("Safi Bathing Zone");
                safiBathingZone.setType("BATHING");
                safiBathingZone.setColor("blue");
                safiBathingZone.setCoordinates("{\"type\":\"Polygon\",\"coordinates\":[[[-9.245183,32.305507],[-9.230183,32.305507],[-9.230183,32.293507],[-9.245183,32.293507],[-9.245183,32.305507]]]}");
                safiBathingZone.setRules(Arrays.asList("No surfing", "Swim between flags"));
                safiBathingZone.setBeach(safiBeach);
                zoneRepository.save(safiBathingZone);
                System.out.println("Saved Safi Bathing Zone: ID=" + safiBathingZone.getId());

                safiBeach.setZones(Arrays.asList(safiBathingZone));
                beachRepository.save(safiBeach);
                System.out.println("Updated Safi Beach with zones: ID=" + safiBeach.getId());

                System.out.println("Database initialization completed (beaches and zones only).");
            } else {
                System.out.println("Database already initialized with beaches. Skipping initialization.");
            }
        };
    }

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SurfApplication.class, args);
    }
}