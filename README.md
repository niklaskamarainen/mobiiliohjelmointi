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
    Composessa näkymä päivittyy automaattisesti kun tila muuttuu.

Kerro, miksi ViewModel on parempi kuin pelkkä remember.
    remember muistaa asioita vain niin kauan kuin näkymä on ruudulla. Tieto katoaa helposti esimerkiksi kun vaihdat näkymää. 
    
    ViewModel säilyttää sovelluksen tilan näkymistä riippumatta, joten käyttäjän tekemät asiat eivät katoa vaikka käyttöliittymä rakennetaan uudelleen.