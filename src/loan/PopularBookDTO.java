package loan;

/**
 * En DTO för att representera statistik över populära böcker.
 */
public record PopularBookDTO(
    String title,
    int loanCount
) {
}
