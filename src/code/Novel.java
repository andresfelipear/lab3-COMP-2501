/**
 * Novel
 *
 * @author Andres Arevalo & Sam
 * @version 1.0
 */
public class Novel
{
    private final String title;
    private final String authorFullName;
    private final int yearPublished;

    /**
     *
     * @param title the title of the book.
     * @param authorFullName the author full name e.g. "Andres Arevalo".
     * @param yearPublished year book was published.
     */
    public Novel(final String title,
            final String authorFullName,
            final int yearPublished)
    {
        this.title = title;
        this.authorFullName = authorFullName;
        this.yearPublished = yearPublished;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthorFullName()
    {
        return authorFullName;
    }

    public int getYearPublished()
    {
        return yearPublished;
    }
}
