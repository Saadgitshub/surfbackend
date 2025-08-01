package com.example.Surf;

import com.example.Surf.Models.*;
import com.example.Surf.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
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
            // Clear existing data to avoid duplicates
            beachRepository.deleteAll();
            zoneRepository.deleteAll();
            alertRepository.deleteAll();
            safetyInfoRepository.deleteAll();
            userRepository.deleteAll();
            System.out.println("Cleared existing data.");

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

            // Alerts for Essaouira
            Alert essaouiraSafeAlert = new Alert();
            essaouiraSafeAlert.setMessage("Safe swimming conditions");
            essaouiraSafeAlert.setType("BATHING");
            essaouiraSafeAlert.setCreatedAt(LocalDateTime.now());
            essaouiraSafeAlert.setBeach(essaouiraBeach);
            essaouiraSafeAlert.setZone(essaouiraBathingZone);
            alertRepository.save(essaouiraSafeAlert);
            System.out.println("Saved Essaouira Safe Alert: ID=" + essaouiraSafeAlert.getId() + ", ZoneID=" + essaouiraSafeAlert.getZone().getId());

            Alert essaouiraDangerAlert = new Alert();
            essaouiraDangerAlert.setMessage("Strong currents detected - Avoid swimming");
            essaouiraDangerAlert.setType("SPORTS");
            essaouiraDangerAlert.setCreatedAt(LocalDateTime.now());
            essaouiraDangerAlert.setBeach(essaouiraBeach);
            essaouiraDangerAlert.setZone(essaouiraKitesurfZone);
            alertRepository.save(essaouiraDangerAlert);
            System.out.println("Saved Essaouira Danger Alert: ID=" + essaouiraDangerAlert.getId() + ", ZoneID=" + essaouiraDangerAlert.getZone().getId());

            // Alert for Safi
            Alert safiSafeAlert = new Alert();
            safiSafeAlert.setMessage("Safe swimming conditions");
            safiSafeAlert.setType("BATHING");
            safiSafeAlert.setCreatedAt(LocalDateTime.now());
            safiSafeAlert.setBeach(safiBeach);
            safiSafeAlert.setZone(safiBathingZone);
            alertRepository.save(safiSafeAlert);
            System.out.println("Saved Safi Safe Alert: ID=" + safiSafeAlert.getId() + ", ZoneID=" + safiSafeAlert.getZone().getId());

            // Test User for Safi
            User testUser = new User();
            testUser.setDeviceId("test-device");
            testUser.setLatitude(32.299507);
            testUser.setLongitude(-9.237183);
            userRepository.save(testUser);
            System.out.println("Saved Test User: deviceId=" + testUser.getDeviceId() + ", coords=[" + testUser.getLatitude() + ", " + testUser.getLongitude() + "]");

            // Safety Rules - French (Essaouira and Safi)
            SafetyInfo safetyRule1Fr = new SafetyInfo();
            safetyRule1Fr.setTitle("Respectez les drapeaux de baignade");
            safetyRule1Fr.setCategory("SAFETY");
            safetyRule1Fr.setContent("{\"icon\":\"flag-outline\",\"description\":\"Vert : baignade autorisée\\nOrange : baignade surveillée\\nRouge : baignade interdite\",\"lang\":\"fr\"}");
            safetyRule1Fr.setBeach(essaouiraBeach);

            SafetyInfo safetyRule2Fr = new SafetyInfo();
            safetyRule2Fr.setTitle("Nagez près des postes de secours");
            safetyRule2Fr.setCategory("SAFETY");
            safetyRule2Fr.setContent("{\"icon\":\"people-outline\",\"description\":\"Restez toujours dans les zones surveillées par les maîtres-nageurs sauveteurs\",\"lang\":\"fr\"}");
            safetyRule2Fr.setBeach(essaouiraBeach);

            SafetyInfo safetyRule3Fr = new SafetyInfo();
            safetyRule3Fr.setTitle("Protection solaire obligatoire");
            safetyRule3Fr.setCategory("SAFETY");
            safetyRule3Fr.setContent("{\"icon\":\"sunny-outline\",\"description\":\"Crème solaire SPF 30+, chapeau et vêtements protecteurs recommandés\",\"lang\":\"fr\"}");
            safetyRule3Fr.setBeach(essaouiraBeach);

            SafetyInfo safetyRule4Fr = new SafetyInfo();
            safetyRule4Fr.setTitle("Attention aux courants");
            safetyRule4Fr.setCategory("SAFETY");
            safetyRule4Fr.setContent("{\"icon\":\"water-outline\",\"description\":\"En cas de courant fort, nagez parallèlement à la côte pour en sortir\",\"lang\":\"fr\"}");
            safetyRule4Fr.setBeach(essaouiraBeach);

            // Safety Rules - English (Essaouira and Safi)
            SafetyInfo safetyRule1En = new SafetyInfo();
            safetyRule1En.setTitle("Respect swimming flags");
            safetyRule1En.setCategory("SAFETY");
            safetyRule1En.setContent("{\"icon\":\"flag-outline\",\"description\":\"Green: swimming allowed\\nOrange: supervised swimming\\nRed: swimming prohibited\",\"lang\":\"en\"}");
            safetyRule1En.setBeach(essaouiraBeach);

            SafetyInfo safetyRule2En = new SafetyInfo();
            safetyRule2En.setTitle("Swim near lifeguard stations");
            safetyRule2En.setCategory("SAFETY");
            safetyRule2En.setContent("{\"icon\":\"people-outline\",\"description\":\"Always stay in areas supervised by lifeguards\",\"lang\":\"en\"}");
            safetyRule2En.setBeach(essaouiraBeach);

            SafetyInfo safetyRule3En = new SafetyInfo();
            safetyRule3En.setTitle("Sun protection required");
            safetyRule3En.setCategory("SAFETY");
            safetyRule3En.setContent("{\"icon\":\"sunny-outline\",\"description\":\"SPF 30+ sunscreen, hat and protective clothing recommended\",\"lang\":\"en\"}");
            safetyRule3En.setBeach(essaouiraBeach);

            SafetyInfo safetyRule4En = new SafetyInfo();
            safetyRule4En.setTitle("Beware of currents");
            safetyRule4En.setCategory("SAFETY");
            safetyRule4En.setContent("{\"icon\":\"water-outline\",\"description\":\"In strong current, swim parallel to shore to escape\",\"lang\":\"en\"}");
            safetyRule4En.setBeach(essaouiraBeach);

            // Lifeguard Stations - French (Essaouira)
            SafetyInfo lifeguard1Fr = new SafetyInfo();
            lifeguard1Fr.setTitle("Poste Central");
            lifeguard1Fr.setCategory("LIFEGUARD");
            lifeguard1Fr.setContent("{\"hours\":\"9h00 - 19h00\",\"location\":\"Centre plage\",\"status\":\"active\",\"lang\":\"fr\"}");
            lifeguard1Fr.setBeach(essaouiraBeach);

            SafetyInfo lifeguard2Fr = new SafetyInfo();
            lifeguard2Fr.setTitle("Poste Nord");
            lifeguard2Fr.setCategory("LIFEGUARD");
            lifeguard2Fr.setContent("{\"hours\":\"10h00 - 18h00\",\"location\":\"Extrémité nord\",\"status\":\"active\",\"lang\":\"fr\"}");
            lifeguard2Fr.setBeach(essaouiraBeach);

            SafetyInfo lifeguard3Fr = new SafetyInfo();
            lifeguard3Fr.setTitle("Poste Sud");
            lifeguard3Fr.setCategory("LIFEGUARD");
            lifeguard3Fr.setContent("{\"hours\":\"10h00 - 18h00\",\"location\":\"Extrémité sud\",\"status\":\"inactive\",\"lang\":\"fr\"}");
            lifeguard3Fr.setBeach(essaouiraBeach);

            // Lifeguard Stations - English (Essaouira)
            SafetyInfo lifeguard1En = new SafetyInfo();
            lifeguard1En.setTitle("Central Station");
            lifeguard1En.setCategory("LIFEGUARD");
            lifeguard1En.setContent("{\"hours\":\"9h00 - 19h00\",\"location\":\"Beach center\",\"status\":\"active\",\"lang\":\"en\"}");
            lifeguard1En.setBeach(essaouiraBeach);

            SafetyInfo lifeguard2En = new SafetyInfo();
            lifeguard2En.setTitle("North Station");
            lifeguard2En.setCategory("LIFEGUARD");
            lifeguard2En.setContent("{\"hours\":\"10h00 - 18h00\",\"location\":\"North end\",\"status\":\"active\",\"lang\":\"en\"}");
            lifeguard2En.setBeach(essaouiraBeach);

            SafetyInfo lifeguard3En = new SafetyInfo();
            lifeguard3En.setTitle("South Station");
            lifeguard3En.setCategory("LIFEGUARD");
            lifeguard3En.setContent("{\"hours\":\"10h00 - 18h00\",\"location\":\"South end\",\"status\":\"inactive\",\"lang\":\"en\"}");
            lifeguard3En.setBeach(essaouiraBeach);

            // Facilities - French (Essaouira and Safi)
            SafetyInfo facility1Fr = new SafetyInfo();
            facility1Fr.setTitle("Douches");
            facility1Fr.setCategory("FACILITY");
            facility1Fr.setContent("{\"icon\":\"water\",\"type\":\"shower\",\"count\":6,\"locations\":\"Réparties sur la plage\",\"lang\":\"fr\"}");
            facility1Fr.setBeach(essaouiraBeach);

            SafetyInfo facility2Fr = new SafetyInfo();
            facility2Fr.setTitle("Parking");
            facility2Fr.setCategory("FACILITY");
            facility2Fr.setContent("{\"icon\":\"car\",\"type\":\"parking\",\"count\":150,\"locations\":\"3 zones principales\",\"lang\":\"fr\"}");
            facility2Fr.setBeach(essaouiraBeach);

            SafetyInfo facility3Fr = new SafetyInfo();
            facility3Fr.setTitle("Restauration");
            facility3Fr.setCategory("FACILITY");
            facility3Fr.setContent("{\"icon\":\"restaurant\",\"type\":\"food\",\"count\":4,\"locations\":\"Bars de plage\",\"lang\":\"fr\"}");
            facility3Fr.setBeach(essaouiraBeach);

            SafetyInfo facility4Fr = new SafetyInfo();
            facility4Fr.setTitle("Accessibilité");
            facility4Fr.setCategory("FACILITY");
            facility4Fr.setContent("{\"icon\":\"accessibility\",\"type\":\"accessibility\",\"count\":2,\"locations\":\"Accès PMR\",\"lang\":\"fr\"}");
            facility4Fr.setBeach(essaouiraBeach);

            // Facilities - English (Essaouira and Safi)
            SafetyInfo facility1En = new SafetyInfo();
            facility1En.setTitle("Showers");
            facility1En.setCategory("FACILITY");
            facility1En.setContent("{\"icon\":\"water\",\"type\":\"shower\",\"count\":6,\"locations\":\"Distributed across beach\",\"lang\":\"en\"}");
            facility1En.setBeach(essaouiraBeach);

            SafetyInfo facility2En = new SafetyInfo();
            facility2En.setTitle("Parking");
            facility2En.setCategory("FACILITY");
            facility2En.setContent("{\"icon\":\"car\",\"type\":\"parking\",\"count\":150,\"locations\":\"3 main areas\",\"lang\":\"en\"}");
            facility2En.setBeach(essaouiraBeach);

            SafetyInfo facility3En = new SafetyInfo();
            facility3En.setTitle("Food & Drink");
            facility3En.setCategory("FACILITY");
            facility3En.setContent("{\"icon\":\"restaurant\",\"type\":\"food\",\"count\":4,\"locations\":\"Beach bars\",\"lang\":\"en\"}");
            facility3En.setBeach(essaouiraBeach);

            SafetyInfo facility4En = new SafetyInfo();
            facility4En.setTitle("Accessibility");
            facility4En.setCategory("FACILITY");
            facility4En.setContent("{\"icon\":\"accessibility\",\"type\":\"accessibility\",\"count\":2,\"locations\":\"Wheelchair access\",\"lang\":\"en\"}");
            facility4En.setBeach(essaouiraBeach);

            // Emergency Contacts (Essaouira and Safi)
            SafetyInfo emergency1 = new SafetyInfo();
            emergency1.setTitle("SAMU");
            emergency1.setCategory("EMERGENCY");
            emergency1.setContent("{\"emoji\":\"🚨\",\"number\":\"15\",\"color\":\"#ef4444\"}");
            emergency1.setBeach(essaouiraBeach);

            SafetyInfo emergency2Fr = new SafetyInfo();
            emergency2Fr.setTitle("Police");
            emergency2Fr.setCategory("EMERGENCY");
            emergency2Fr.setContent("{\"emoji\":\"🚓\",\"number\":\"17\",\"color\":\"#3b82f6\",\"lang\":\"fr\"}");
            emergency2Fr.setBeach(essaouiraBeach);

            SafetyInfo emergency2En = new SafetyInfo();
            emergency2En.setTitle("Police");
            emergency2En.setCategory("EMERGENCY");
            emergency2En.setContent("{\"emoji\":\"🚓\",\"number\":\"17\",\"color\":\"#3b82f6\",\"lang\":\"en\"}");
            emergency2En.setBeach(essaouiraBeach);

            SafetyInfo emergency3Fr = new SafetyInfo();
            emergency3Fr.setTitle("Pompiers");
            emergency3Fr.setCategory("EMERGENCY");
            emergency3Fr.setContent("{\"emoji\":\"🚒\",\"number\":\"18\",\"color\":\"#f97316\",\"lang\":\"fr\"}");
            emergency3Fr.setBeach(essaouiraBeach);

            SafetyInfo emergency3En = new SafetyInfo();
            emergency3En.setTitle("Fire Department");
            emergency3En.setCategory("EMERGENCY");
            emergency3En.setContent("{\"emoji\":\"🚒\",\"number\":\"18\",\"color\":\"#f97316\",\"lang\":\"en\"}");
            emergency3En.setBeach(essaouiraBeach);

            SafetyInfo emergency4Fr = new SafetyInfo();
            emergency4Fr.setTitle("Urgence EU");
            emergency4Fr.setCategory("EMERGENCY");
            emergency4Fr.setContent("{\"emoji\":\"🆘\",\"number\":\"112\",\"color\":\"#8b5cf6\",\"lang\":\"fr\"}");
            emergency4Fr.setBeach(essaouiraBeach);

            SafetyInfo emergency4En = new SafetyInfo();
            emergency4En.setTitle("EU Emergency");
            emergency4En.setCategory("EMERGENCY");
            emergency4En.setContent("{\"emoji\":\"🆘\",\"number\":\"112\",\"color\":\"#8b5cf6\",\"lang\":\"en\"}");
            emergency4En.setBeach(essaouiraBeach);

            SafetyInfo emergency5Fr = new SafetyInfo();
            emergency5Fr.setTitle("Sauvetage mer");
            emergency5Fr.setCategory("EMERGENCY");
            emergency5Fr.setContent("{\"emoji\":\"🌊\",\"number\":\"196\",\"color\":\"#06b6d4\",\"lang\":\"fr\"}");
            emergency5Fr.setBeach(essaouiraBeach);

            SafetyInfo emergency5En = new SafetyInfo();
            emergency5En.setTitle("Sea Rescue");
            emergency5En.setCategory("EMERGENCY");
            emergency5En.setContent("{\"emoji\":\"🌊\",\"number\":\"196\",\"color\":\"#06b6d4\",\"lang\":\"en\"}");
            emergency5En.setBeach(essaouiraBeach);

            // Save safety info for Essaouira
            safetyInfoRepository.saveAll(Arrays.asList(
                safetyRule1Fr, safetyRule2Fr, safetyRule3Fr, safetyRule4Fr,
                safetyRule1En, safetyRule2En, safetyRule3En, safetyRule4En,
                lifeguard1Fr, lifeguard2Fr, lifeguard3Fr,
                lifeguard1En, lifeguard2En, lifeguard3En,
                facility1Fr, facility2Fr, facility3Fr, facility4Fr,
                facility1En, facility2En, facility3En, facility4En,
                emergency1, emergency2Fr, emergency2En, emergency3Fr, emergency3En,
                emergency4Fr, emergency4En, emergency5Fr, emergency5En
            ));
            System.out.println("Saved safety info for Essaouira");

            // Save safety info for Safi
            SafetyInfo safiSafetyRule1Fr = new SafetyInfo();
            safiSafetyRule1Fr.setTitle("Respectez les drapeaux de baignade");
            safiSafetyRule1Fr.setCategory("SAFETY");
            safiSafetyRule1Fr.setContent("{\"icon\":\"flag-outline\",\"description\":\"Vert : baignade autorisée\\nOrange : baignade surveillée\\nRouge : baignade interdite\",\"lang\":\"fr\"}");
            safiSafetyRule1Fr.setBeach(safiBeach);

            SafetyInfo safiSafetyRule1En = new SafetyInfo();
            safiSafetyRule1En.setTitle("Respect swimming flags");
            safiSafetyRule1En.setCategory("SAFETY");
            safiSafetyRule1En.setContent("{\"icon\":\"flag-outline\",\"description\":\"Green: swimming allowed\\nOrange: supervised swimming\\nRed: swimming prohibited\",\"lang\":\"en\"}");
            safiSafetyRule1En.setBeach(safiBeach);

            SafetyInfo safiEmergency1 = new SafetyInfo();
            safiEmergency1.setTitle("SAMU");
            safiEmergency1.setCategory("EMERGENCY");
            safiEmergency1.setContent("{\"emoji\":\"🚨\",\"number\":\"15\",\"color\":\"#ef4444\"}");
            safiEmergency1.setBeach(safiBeach);

            safetyInfoRepository.saveAll(Arrays.asList(
                safiSafetyRule1Fr, safiSafetyRule1En, safiEmergency1
            ));
            System.out.println("Saved safety info for Safi");

            System.out.println("Database initialization completed.");
        };
    }

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SurfApplication.class, args);
    }
}