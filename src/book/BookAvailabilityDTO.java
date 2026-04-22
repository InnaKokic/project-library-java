package book;

public record BookAvailabilityDTO(
        int id,
        String title,
        int availableCopies
) {
}
