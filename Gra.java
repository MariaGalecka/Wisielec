import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gra {
    private static int zwyciestwa = 0;
    private static int porazki = 0;
    private static int sumaProb = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Witaj w grze Wisielec!");

        boolean graDalej = true;
        while (graDalej) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Zagraj w Wisielca");
            System.out.println("2. Edytuj bazę słów");
            System.out.println("3. Wyświetl statystyki");
            System.out.println("4. Zakończ");

            int wybor = scanner.nextInt();
            scanner.nextLine(); // pozbycie się znaku nowej linii

            switch (wybor) {
                case 1:
                    graj(scanner, random);
                    break;
                case 2:
                    edytujBazeSlow(scanner);
                    break;
                case 3:
                    wyswietlStatystyki();
                    break;
                case 4:
                    graDalej = false;
                    break;
                default:
                    System.out.println("Niepoprawny wybór, spróbuj ponownie.");
            }
        }


        System.out.println("Dziękujemy za grę!");
    }

    private static void graj(Scanner scanner, Random random) {
        boolean graDalej = true;
        while (graDalej) {
            // wczytanie słów z pliku
            List<String> slowa = wczytajSlowa();

            // wybór poziomu trudności
            System.out.println("Wybierz poziom trudności");
            System.out.println("1. Łatwy");
            System.out.println("2. Średni");
            System.out.println("3. Trudny");
            int poziom = scanner.nextInt();
            scanner.nextLine(); // pozbycie się znaku nowej linii

            String slowo = wybierzSlowo(slowa, poziom, random);
            if (slowo.isEmpty()) {
                System.out.println("Brak słów w wybranym poziomie trudności.");
                continue;
            }
            char[] ukryteSlowo = new char[slowo.length()];
            for (int i = 0; i < slowo.length(); i++) {
                ukryteSlowo[i] = '_';
            }
            int bledy = 0;
            boolean wygrana = false;
            while (bledy < 6) {
                // Wyświetlenie stanu gry
                System.out.println("Słowo: " + String.valueOf(ukryteSlowo));
                System.out.println("Błędy: " + bledy + "/6");

                // Odczytanie zgadywanego znaku
                System.out.println("Podaj literę:");
                char litera = scanner.nextLine().toUpperCase().charAt(0); // konwersacja na wielką literę

                // Sprawdzenie czy litera znajduje się w słowie
                boolean trafiony = false;
                for (int i = 0; i < slowo.length(); i++) {
                    if (Character.toUpperCase(slowo.charAt(i)) == litera) { // Konwersja na wielką literę
                        ukryteSlowo[i] = litera;
                        trafiony = true;
                    }
                }

                // Jeśli nie trafiono, zwiększ liczbę błędów
                if (!trafiony) {
                    bledy++;
                    Wisielec.wyswietlWisielca(bledy); // Wyświetl wisielca tylko po błędzie
                }

                // Sprawdź czy wygrana
                if (String.valueOf(ukryteSlowo).indexOf('_') == -1) {
                    wygrana = true;
                    break;
                }
            }

            // Wynik gry
            if (wygrana) {
                zwyciestwa++;
                sumaProb += (bledy + 1);
                System.out.println("Gratulacje, wygrałeś! Szukane słowo to: " + slowo);
            } else {
                porazki++;
                sumaProb += 6;
                Wisielec.wyswietlWisielca(6); // Wyświetl pełnego wisielca w przypadku przegranej
                System.out.println("Przegrałeś! Szukane słowo to: " + slowo);
            }

            // Pytanie o chęć kontynuacji gry
            System.out.println("Czy chcesz zagrać ponownie? (T/N)");
            String kontynuuj = scanner.nextLine();
            if (!kontynuuj.equalsIgnoreCase("T")) {
                graDalej = false;
            }
        }
    }
    private static void wyswietlStatystyki() {
        System.out.println("Statystyki gry:");
        System.out.println("Liczba zwycięstw: " + zwyciestwa);
        System.out.println("Liczba porażek: " + porazki);
        System.out.println("Suma prób: " + sumaProb);
    }


    private static void edytujBazeSlow(Scanner scanner) {
        System.out.println("Wybierz opcję edycji bazy słów:");
        System.out.println("1. Dodaj nowe słowo");
        System.out.println("2. Usuń istniejące słowo");

        int wybor = scanner.nextInt();
        scanner.nextLine(); // pozbycie się znaku nowej linii

        switch (wybor) {
            case 1:
                System.out.println("Podaj słowo do dodania:");
                String noweSlowo = scanner.nextLine();
                dodajSlowo(noweSlowo);
                break;
            case 2:
                System.out.println("Podaj słowo do usunięcia:");
                String usuwaneSlowo = scanner.nextLine();
                usunSlowo(usuwaneSlowo);
                break;
            default:
                System.out.println("Niepoprawny wybór, spróbuj ponownie.");
        }
    }

    private static List<String> wczytajSlowa() {
        List<String> slowa = new ArrayList<>();
        try {
            File plik = new File("C:\\Users\\mariagalecka\\Documents\\slownik.txt");
            if (!plik.exists()) {
                plik.createNewFile();
            }
            Scanner scanner = new Scanner(plik);
            while (scanner.hasNextLine()) {
                slowa.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania słów.");
            e.printStackTrace();
        }
        return slowa;
    }

    private static String wybierzSlowo(List<String> slowa, int poziom, Random random) {
        List<String> slowaDoWyboru = new ArrayList<>();
        for (String slowo : slowa) {
            if (poziom == 1 && slowo.length() <= 6) {
                slowaDoWyboru.add(slowo);
            } else if (poziom == 2 && slowo.length() > 6 && slowo.length() <= 10) {
                slowaDoWyboru.add(slowo);
            } else if (poziom == 3 && slowo.length() > 10) {
                slowaDoWyboru.add(slowo);
            }
        }
        if (slowaDoWyboru.isEmpty()) {
            System.out.println("Brak słów w wybranym poziomie trudności.");
            return "";
        }
        return slowaDoWyboru.get(random.nextInt(slowaDoWyboru.size()));
    }

    // Dodanie nowego słowa do bazy
    private static void dodajSlowo(String slowo) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\mariagalecka\\Documents\\slownik.txt", true);
            writer.write(slowo + "\n");
            writer.close();
            System.out.println("Słowo zostało dodane do bazy.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas dodawania słowa do bazy.");
            e.printStackTrace();
        }
    }

    // Usunięcie słowa z bazy
    private static void usunSlowo(String slowo) {
        List<String> slowa = wczytajSlowa();
        if (slowa.remove(slowo)) {
            try {
                FileWriter writer = new FileWriter("C:\\Users\\mariagalecka\\Documents\\slownik.txt");
                for (String s : slowa) {
                    writer.write(s + "\n");
                }
                writer.close();
                System.out.println("Słowo zostało usunięte z bazy.");
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas usuwania słowa z bazy.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Nie znaleziono słowa w bazie.");
        }
    }
}


class Wisielec {
    private static final String[] WISIELEC = {
            "    +---+\n    |   |\n        |\n        |\n        |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n        |\n        |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n    |   |\n        |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n   /|   |\n        |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n   /|\\  |\n        |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n   /|\\  |\n   /    |\n        |\n  =========",
            "    +---+\n    |   |\n    O   |\n   /|\\  |\n   / \\  |\n        |\n  ========="
    };

    public static void wyswietlWisielca(int bledy) {
        System.out.println(WISIELEC[bledy]);
    }
}
