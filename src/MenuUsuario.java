import java.io.IOException;
import java.util.*;

public class Menu {
    private final Map<String, Moneda> monedas;
    private final Scanner scanner = new Scanner(System.in);
    private final ConversorMoneda conversor = new ConversorMoneda();

    public Menu() {
        monedas = new LinkedHashMap<>();
        monedas.put("USD", new Moneda("USD", "Dólar estadounidense"));
        monedas.put("ARS", new Moneda("ARS", "Peso argentino"));
        monedas.put("BOB", new Moneda("BOB", "Boliviano boliviano"));
        monedas.put("BRL", new Moneda("BRL", "Real brasileño"));
        monedas.put("CLP", new Moneda("CLP", "Peso chileno"));
        monedas.put("COP", new Moneda("COP", "Peso colombiano"));
    }

    public void mostrar() {
        while (true) {
            System.out.println("💱 Conversor de Moneda");
            System.out.println("Monedas disponibles:");
            monedas.values().forEach(moneda -> System.out.println("- " + moneda));

            System.out.print("\nIngrese moneda de origen: ");
            String origen = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese moneda destino: ");
            String destino = scanner.nextLine().toUpperCase();

            if (!monedas.containsKey(origen) || !monedas.containsKey(destino)) {
                System.out.println("❌ Moneda inválida.\n");
                continue;
            }

            System.out.print("Ingrese monto en " + origen + ": ");
            double monto = scanner.nextDouble();
            scanner.nextLine(); // limpiar buffer

            try {
                double resultado = conversor.convertir(origen, destino, monto);
                System.out.printf("✅ %.2f %s = %.2f %s\n\n",
                        monto, origen, resultado, destino);
            } catch (IOException e) {
                System.out.println("Error de conexión: " + e.getMessage());
            }

            System.out.print("¿Otra conversión? (s/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("s")) break;
        }

        System.out.println("Gracias por usar el conversor.");
    }
}
