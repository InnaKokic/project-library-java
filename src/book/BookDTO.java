package book;

public record BookDTO(
        int id,
        String title,
        String authors,
        String isbn,
        int year,
        int availableCopies,
        String language,
        String summary
) {
    // En record i Java är ett sätt att definiera en enkel klass som främst
    // används för att hålla data – utan att du behöver skriva
    // all “boilerplate”-kod själv.
    //Här slipper man skriva egna konstruktor, getters,
    // toString(), equals() och hashCode().
    // En record gör allt det automatiskt.
}
