# task app
TaskApp on yksinkertainen Compose-sovellus.

Task on data class, joka kuvaa yksittäistä tehtävää.
Mock-dataa käytetään UI:n testaamiseen.

Sovellus sisältää puhtaita Kotlin-funktioita:
- addTask
- toggleDone
- filterByDone
- sortByDueDate

UI kutsuu näitä funktioita napeilla ja näyttää tehtävälistan.

# week2
Selitä miten compose-tilanhallinta toimii.
- Composessa näkymä päivittyy automaattisesti kun tila muuttuu.

Kerro, miksi ViewModel on parempi kuin pelkkä remember.
- remember muistaa asioita vain niin kauan kuin näkymä on ruudulla. Tieto katoaa helposti esimerkiksi kun vaihdat näkymää. 
    
- ViewModel säilyttää sovelluksen tilan näkymistä riippumatta, joten käyttäjän tekemät asiat eivät katoa vaikka käyttöliittymä rakennetaan uudelleen.

# week3
Selitä MVVM, miksi se on hyödyllinen Compose-sovelluksissa.

- MVVM jakaa sovelluksen näkymään, logiikkaan ja dataan, jolloin vastuut eivät mene sekaisin. 
- Compose-sovelluksissa se on hyödyllinen, koska UI reagoi automaattisesti ViewModelin tilamuutoksiin ja koodi pysyy selkeänä.

Kerro miten StateFlow toimii.

- StateFlow pitää aina viimeisimmän arvon muistissa. Kun arvo muuttuu, kaikki sitä seuraavat näkymät päivittyvät automaattisesti.

# week4
Mitä tarkoittaa navigointi JetPack Composessa

-Eri näkymien välillä siirrytään ilman perinteisiä activityjä

Mitä ovat NavHost ja NavController

-NavController vastaa: minne siirrytään ja milloin

-NavHost määrittelee mitkä ruudut sovelluksessa on ja millä reiteillä niihin navigoidaan

Miten sovelluksesi navigaatiorakenn on toteutettu

-Sovelluksessa on kaksi päänäkymää: HomeScreen ja CalendarScreen. Navigointi toimii molempiin suuntiin, eli Home->Calendar tai Calendar->Home

Miten MVVM ja navigointi yhdistyvät

-Sama TaskViewModel jaetaan molemmille screeneille navigoinnin kautta

Miten ViewModelin tila jaetaan kummankin ruudun välillä

-ViewModel sisältää tehtävälistan ja molemmat screenit kuuntelevat samaa tilaa collectAsState() avulla. Kun tehtäviä lisätään tai muokataan, molemmat näkymät päivittyvät automaattisesti.

Miten CalendarScreen on toteutettu

-Tehtävät ryhmitellään dueDate mukaan. dueDate toimii otsikkona ja sen alla sen päivän tehtävät klikattavina kortteina.

Miten alertDialog hoitaa addTask ja editTask

-Käyttäjä syöttää haluamansa tiedot kenttiin ja painaa save. addTask kutsuu viewModelin addTask() ja editTask kutsuu viewModelin updateTask(). Delete kutsuu viewModelin removeTask().