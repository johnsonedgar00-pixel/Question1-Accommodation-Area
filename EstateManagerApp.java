import java.util.Scanner;

public class EstateManagerApp {
    static class Area {
        String name;
        int occupants = 0;
        boolean[] lights = new boolean[3]; // three lights

        Area(String name) {
            this.name = name;
        }

        void add(int n) { occupants += n; }
        void remove(int n) { occupants = Math.max(0, occupants - n); }
        void on(int light) { if (light >= 1 && light <= 3) lights[light - 1] = true; }
        void off(int light) { if (light >= 1 && light <= 3) lights[light - 1] = false; }

        void report() {
            System.out.println("\n--- STATUS REPORT ---");
            System.out.println("Area: " + name);
            System.out.println("Occupants: " + occupants);
            for (int i = 0; i < 3; i++)
                System.out.println("Light " + (i + 1) + ": " + (lights[i] ? "ON" : "OFF"));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Area gym = new Area("Gym Area");
        Area pool = new Area("Swimming Area");
        Area active = gym;

        System.out.println("=== Speke Apartments Area Control System ===");

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("S – Select active area (G=Gym, P=Swimming)");
            System.out.println("W – Add occupants");
            System.out.println("X – Remove occupants");
            System.out.println("Y – Switch ON light (1–3)");
            System.out.println("Z – Switch OFF light (1–3)");
            System.out.println("R – Report status");
            System.out.println("Q – Quit");
            System.out.print("Choice: ");

            String c = in.nextLine().trim().toUpperCase();

            switch (c) {
                case "S" -> {
                    System.out.print("Select area (G/P): ");
                    String a = in.nextLine().trim().toUpperCase();
                    active = (a.equals("G")) ? gym : (a.equals("P")) ? pool : active;
                    System.out.println("Active area: " + active.name);
                }
                case "W" -> active.add(validInt(in, "Number to ADD: "));
                case "X" -> active.remove(validInt(in, "Number to REMOVE: "));
                case "Y" -> active.on(validLight(in, "Light number (1–3) to switch ON: "));
                case "Z" -> active.off(validLight(in, "Light number (1–3) to switch OFF: "));
                case "R" -> active.report();
                case "Q" -> {
                    System.out.println("Exiting system... Goodbye!");
                    in.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // Helper: validate integer input
    static int validInt(Scanner in, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                int n = Integer.parseInt(in.nextLine());
                if (n >= 0) return n;
            } catch (Exception ignored) {}
            System.out.println("Invalid number. Try again.");
        }
    }

    // Helper: validate light number (1–3)
    static int validLight(Scanner in, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                int n = Integer.parseInt(in.nextLine());
                if (n >= 1 && n <= 3) return n;
            } catch (Exception ignored) {}
            System.out.println("Invalid light number (must be 1–3).");
        }
    }
}
