# Gra w wisielca

## Spis treści
1. Opis projektu
2. Funkcjonalności
3. Instrukcja obsługi

## 1. Opis projektu<p>
Projekt "Gra w Wisielca" napisany w całóści w języku java, powstwał w ramach projektu zaliczeniowego laboratoriów z przedmiotu "Podsatwy Programowania". 
Gra w Wisielca jest klasyczną grą tekstową, w której gracz próbuje odgadnąć ukryte słowo, zgadując pojedyńcze litery. 
Gracz ma ograniczoną liczbę błędów przed przegraną. W tej grze liczba błędów wynosi 6.

## 2. Funkcjonalności<p>
* wybór losowego słowa z bazy słow,
* możliwość edytowania/ dodawania bazy słów,
* możliwość wyboru poziomu trudności (łatwy, średni, trudny),
* dostęp do statystyk (ilość zwycięstw, porażek, po ilu próbach).

W tej grze istnieje możliwość wyboru losowego słowa z bazy słów. W bazie słów znajduje się lista 91 polskich słów, 
które można edytowawać poprzez dodawanie lub usuwanie. Kolejną funkcjonalnością jest możliwość wyboru przez gracza poziomu trudności (łatwy, średni, trudny). 
W grze przewidziano również dostęp do statystyk. W ramach tej
funcjonalności gracz może zobaczyć ilość zwycięstw, porażek oraz po ilu próbach.

## 3. Instrukcja Obsługi<p>
### Wymagania
* Java Development Kit (JDK)
* Plik słownika 'slownik.txt' znajdujący się w tym samym katalogu co Gra.java

### Uruchomienie aplikacji
* Skopiuj pliki 'Gra.java' i 'slownik.txt' do jednego katalogu na swoim komputerze
* Aby skompilować plik 'Gra.java' otwórz terminal i przejdź do katalogu w którym znajdują się pliki 'Gra.java' i 'slownik.txt'. Następnie użyj komendy: ```sh javac Gra.java
* Uruchom aplikację: 'Gra'


### Edycja bazy słów
<p>W celu edycji bazy słów kiedy program zapyta o wybranie opcji, należy wpisać '2' i nacisnąć enter. W kolejnym kroku 
w przypadku chęci dodania nowego słowa należy wpisać '1' i wcisnąć enter. Każde słowo z bazy można również usunąć
wpisując z kolei '2' i wciskając enter.

### Dostęp do statystyk
W celu skorzystania z opcji dotępu do statystyk, kiedy program zapyta o wybranie opcji należy wpisać '3' i wcisnąć enter. W ten sposób
program pokaże liczbę zwycięztw, liczbę porażek oraz sumę prób.

### Kod źródłowy
Kod źródłowy znajduje się w pliku 'Gra.java'.
