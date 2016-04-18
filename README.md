# Problem
Plansza o wymiarach 6 x 6 zawiera N samochodów. Samochody na planszyu są ustawione w pionie albo w poziomie, oraz zajmują dwa lub trzy pola na planszy.
Adres pola planszy składa się z punktu (x,y), gdzie x i y są z przedziału [0,5] oraz lewy dolny róg planszy ma współrzędne (0,0) natomiast prawy górny róg współrzędne (5,5).
Samochody mogą się poruszać tylko do przodu lub w tyłu zgodnie ze swoim położeniem. Samochody nie mogą wyjeżdzać poza granice planszy lub najeżdzać na siebie.
Samochód oznaczony identyfikatorem X, który musi osiągnąć pole o adresie (5,3) jednym swoim końcem.
Zadanie polega na podaniu listy ruchów poszczególnych samochodów umożliwiającej dotarcie do pola o adresie (5,3) i opuszczenia przez samochódu z identyfikatorem X planszy.
Pod uwagę będzie brana również pojedyncza liczba przesunięć każdego samochodu, tj. ruch samochodu w prawo o x pól będzie liczony jako x.

# Input
Algorytm jest uruchamiany kilkukrotnie z przykładami testowymi o różnej trudności.
Pierwsza linia zawiera liczbę przypadków testowych
Każdy przypadek testowy rozpoczyna się od liczby n samochodów na planszy.
Kolejne n lini zawierają opis każdego samochodu w postaci:
[id] [start point] [direction] [length]
[id] - identyfikatorem samochodu jako duża litera
[start point] - punkt znajdujący się najblizej w pionie i poziomie do punktu (0,0)
[direction] - polozenie samochodu wartosci V albo H
[length] - dlugość samochodu

# Output
Dla każdego przypadku testowego należy wyświetlić w jednej lini liczbę n kroków oraz n linii ruchów samochodów z planszy

# Limits
TBD

# Sample

### Input
    1
    3
    X 0 3 H 2
    A 4 1 H 2
    C 4 2 V 3
### Output
    3
    A L 2
    C D 2
    X R 4
    
### Explanation
> Rozwiązania składa się z trzech ruchów o wartościach kolejno 2, 2, 4. Zatem liczba pojedynczych ruchów dla tego rozwiązania jest równa 8.
Samochody w kolejnych ruchach znajdowały się na odpowiednich pozycjach.

#### Stan początkowy
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    |x|x| | |c| |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    | | | | |a|a|
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
#### Ruch 'A L 2'
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    |x|x| | |c| |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    | | |a|a| | |
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
#### Ruch 'C D 2'
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    |x|x| | | | |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    | | |a|a|c| |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
#### Ruch 'X R 4'
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    | | | | | | |
    +-+-+-+-+-+-+
    | | | | |x|x|
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    | | |a|a|c| |
    +-+-+-+-+-+-+
    | | | | |c| |
    +-+-+-+-+-+-+
    
# Technical information
## Wspierane języki programowania:
- C++
- C# (Win10x64, min .Net 4.0 x86)
- Java (jdk1.7, jdk1.8)
- Python (python2.7, python3.4)
