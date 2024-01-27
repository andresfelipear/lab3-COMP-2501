import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BookStore
 *
 * @author Andres Arevalo & Sam
 * @version 1.0
 */
public class BookStore
{
    private final List<Novel> novels;
    private final String name;

    public BookStore(final String name)
    {
        if(name.equalsIgnoreCase("amazon"))
        {
            this.name = "Chapters";
        } else
        {
            this.name = name;

        }

        novels = new ArrayList<>();
        populateNovels(novels);

    }

    public static void main(String[] args)
    {
        BookStore bookStore;

        if(args.length > 0)
        {
            bookStore = new BookStore(args[0]);


            System.out.println("Print All Titles: ");
            bookStore.printAllTitles();
            System.out.println("\n");

            System.out.println("Print Titles Containing: ");
            bookStore.printTitlesContaining("the", true);
            System.out.println("\n");


            final int lengthTitle;
            lengthTitle = 13;
            System.out.println("Print Titles of Length = " + lengthTitle);
            bookStore.printTitlesOfLength(lengthTitle);
            System.out.println("\n");

            System.out.println("Print Authors names starts or ends with: ");
            bookStore.printNameStartsEndsWith("aN");
            System.out.println("\n");


            System.out.println("Print Longest: ");
            System.out.println("Longest xyz = "+ bookStore.getLongest("xyz"));
            System.out.println("Longest AutHor = " + bookStore.getLongest("AutHor"));
            System.out.println("Longest titlE = " + bookStore.getLongest("titlE"));

        }else
        {
            System.out.println("Please include the name of the BookStore as a command line argument");
        }



    }

    /**
     * If the property argument is “author” (in any letter casing) then return the longest author name
     * (by finding it using a foreach loop);
     * if the property argument is “title” (in any letter casing) then return the longest title
     * (by finding it using a foreach loop); if the property argument is something else then return null
     *
     * @param property the type of property to be checked in the novel.
     * @return longest author or longest title or null.
     */
    private String getLongest(final String property)
    {

        if(property.equalsIgnoreCase("title"))
        {
            String longestTitle;
            longestTitle = "";

            for(final Novel novel : novels)
            {
                if(novel != null)
                {
                    if(novel.getTitle() != null && !novel.getTitle().isBlank())
                    {
                        final String novelTitle;
                        novelTitle = novel.getTitle();

                        if(novelTitle.length() > longestTitle.length())
                        {
                            longestTitle = novelTitle;
                        }

                    }


                }
            }

            return longestTitle;

        }else if(property.equalsIgnoreCase("author"))
        {

            String longestAuthorName;
            longestAuthorName = "";

            for(final Novel novel : novels)
            {
                if(novel != null)
                {
                    if(novel.getAuthorFullName() != null && !novel.getAuthorFullName().isBlank())
                    {
                        final String novelAuthorName;
                        novelAuthorName = novel.getAuthorFullName();

                        if(novelAuthorName.length() > longestAuthorName.length())
                        {
                            longestAuthorName = novelAuthorName;
                        }

                    }

                }
            }

            return longestAuthorName;

        }else
        {
            return  null;
        }

    }

    /**
     * +
     * Prints all author names that either start or end with substring, in lowercase…
     * the substring match is case-insensitive (e.g. “aN” is the same as “An”, etc.)
     *
     * @param substring the substring to be look in the author name.
     */
    private void printNameStartsEndsWith(String substring)
    {
        substring = substring.toLowerCase();

        for(final Novel novel : novels)
        {
            if(novel != null)
            {
                String authorFullName = novel.getAuthorFullName();

                if(authorFullName != null && !authorFullName.isBlank())
                {
                    authorFullName = authorFullName.toLowerCase();

                    final boolean startsWith;
                    final boolean endsWith;

                    startsWith = authorFullName.startsWith(substring);
                    endsWith = authorFullName.endsWith(substring);

                    if(startsWith || endsWith)
                    {
                        System.out.println(authorFullName);
                    }

                }
            }

        }
    }

    /**
     * Prints all titles that are of exactly the specified length.
     *
     * @param length the length of the title to be printed.
     */
    private void printTitlesOfLength(final int length)
    {
        for(final Novel novel : novels)
        {
            if(novel != null)
            {
                if(novel.getTitle() != null && !novel.getTitle().isBlank())
                {
                    final int titleLength;
                    titleLength = novel.getTitle().length();

                    if(titleLength == length)
                    {
                        System.out.println(novel.getTitle());
                    }
                }
            }

        }
    }

    /**
     * Prints all titles that contain the specified substring; if caseSensitive is false,
     * then the match is in any letter casing (e.g. “The” is the same as “the”);
     * if caseSensitive is true then the match must include letter casing (e.g. “The” is different than “the”)
     *
     * @param substring     substring must be in the title of the novel.
     * @param caseSensitive if true is case-sensitive if false is not.
     */
    private void printTitlesContaining(String substring,
                                final boolean caseSensitive)
    {
        for(final Novel novel : novels)
        {
            if(novel != null)
            {
                String novelTitle;
                novelTitle = novel.getTitle();

                if(novelTitle != null && !novelTitle.isBlank())
                {
                    if(!caseSensitive)
                    {
                        substring = substring.toLowerCase();
                        novelTitle = novelTitle.toLowerCase();
                    }

                    if(novelTitle.contains(substring))
                    {
                        novelTitle = toTitleCase(novelTitle);
                        System.out.println(novelTitle);
                    }
                }


            }

        }
    }

    /**
     * @param string the string to be transformed.
     * @return the given string in title case.
     */
    private String toTitleCase(final String string)
    {
        String[] stringList;
        StringBuilder stringToTitleCase = new StringBuilder();

        stringList = string.split(" ");

        for(final String word : stringList)
        {
            char[] charList;

            charList = word.toCharArray();
            charList[0] = Character.toTitleCase(charList[0]);
            stringToTitleCase.append(new String(charList)).append(" ");

        }
        return stringToTitleCase.toString();

    }

    /**
     * Prints all titles in UPPERCASE (except null Novels and null titles, which are ignored)
     */
    private void printAllTitles()
    {
        for(final Novel novel : novels)
        {
            if(novel != null)
            {
                if(novel.getTitle() != null && !novel.getTitle().isBlank())
                {
                    System.out.println(novel.getTitle().toUpperCase());
                }
            }

        }

    }

    private void populateNovels(final List<Novel> novels)
    {

        novels.add(new Novel(null, null, 0));
        novels.add(new Novel("The Adventures of Augie March", "Saul Bellow", 1953));
        novels.add(new Novel("All the King’s Men", "Robert Penn Warren", 1946));
        novels.add(new Novel("American Pastoral", "Philip Roth", 1997));
        novels.add(new Novel("An American Tragedy", "Theodore Dreiser", 1925));
        novels.add(new Novel("Animal Farm", "George Orwell", 1946));
        novels.add(new Novel("Appointment in Samarra", "John O'Hara", 1934));
        novels.add(new Novel(null, null, 0));
        novels.add(new Novel(null, "author name 1", 0));
        novels.add(new Novel("", null, 0));
        novels.add(new Novel(null, "", 0));
        novels.add(new Novel("title 1", null, 0));
        novels.add(new Novel("", "", 0));
        novels.add(new Novel("", "author name 2", 0));
        novels.add(new Novel("title 2", null, 0));
        novels.add(new Novel("Are You There God? It's Me, Margaret.", "Judy Blume", 1970));
        novels.add(new Novel("The Assistant", "Bernard Malamud", 1957));
        novels.add(new Novel("At Swim-Two-Birds", "Flann O'Brien", 1938));
        novels.add(new Novel("Atonement", "Ian McEwan", 2002));
        novels.add(new Novel("Beloved", "Toni Morrison", 1987));
        novels.add(new Novel("The Berlin Stories", "Christopher Isherwood", 1946));
        novels.add(new Novel("The Big Sleep", "Raymond Chandler", 1939));
        novels.add(new Novel("The Blind Assassin", "Margaret Atwood", 2000));
        novels.add(new Novel("Blood Meridian", "Cormac McCarthy", 1986));
        novels.add(new Novel("Brideshead Revisited", "Evelyn Waugh", 1946));
        novels.add(new Novel("The Bridge of San Luis Rey", "Thornton Wilder", 1927));
        novels.add(new Novel("Call It Sleep", "Henry Roth", 1935));
        novels.add(new Novel("Catch-22", "Joseph Heller", 1961));
        novels.add(new Novel("The Catcher in the Rye", "J.D. Salinger", 1951));
        novels.add(new Novel("A Clockwork Orange", "Anthony Burgess", 1963));
        novels.add(new Novel("The Confessions of Nat Turner", "William Styron", 1967));
        novels.add(new Novel("The Corrections", "Jonathan Franzen", 2001));
        novels.add(new Novel("The Crying of Lot 49", "Thomas Pynchon", 1966));
        novels.add(new Novel("A Dance to the Music of Time", "Anthony Powell", 1951));
        novels.add(new Novel("The Day of the Locust", "Nathanael West", 1939));
        novels.add(new Novel("Death Comes for the Archbishop", "Willa Cather", 1927));
        novels.add(new Novel("A Death in the Family", "James Agee", 1958));
        novels.add(new Novel("The Death of the Heart", "Elizabeth Bowen", 1958));
        novels.add(new Novel("Deliverance", "James Dickey", 1970));
        novels.add(new Novel("Dog Soldiers", "Robert Stone", 1974));
        novels.add(new Novel("Falconer", "John Cheever", 1977));
        novels.add(new Novel("The French Lieutenant's Woman", "John Fowles", 1969));
        novels.add(new Novel("The Golden Notebook", "Doris Lessing", 1962));
        novels.add(new Novel("Go Tell It on the Mountain", "James Baldwin", 1953));
        novels.add(new Novel("Gone with the Wind", "Margaret Mitchell", 1936));
        novels.add(new Novel("The Grapes of Wrath", "John Steinbeck", 1939));
        novels.add(new Novel("Gravity's Rainbow", "Thomas Pynchon", 1973));
        novels.add(new Novel("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        novels.add(new Novel("A Handful of Dust", "Evelyn Waugh", 1934));
        novels.add(new Novel("The Heart Is a Lonely Hunter", "Carson McCullers", 1940));
        novels.add(new Novel("The Heart of the Matter", "Graham Greene", 1948));
        novels.add(new Novel("Herzog", "Saul Bellow", 1964));
        novels.add(new Novel("Housekeeping", "Marilynne Robinson", 1981));
        novels.add(new Novel("A House for Mr. Biswas", "V.S. Naipaul", 1962));
        novels.add(new Novel("I, Claudius", "Robert Graves", 1934));
        novels.add(new Novel("Infinite Jest", "David Foster Wallace", 1996));
        novels.add(new Novel("Invisible Man", "Ralph Ellison", 1952));
        novels.add(new Novel("Light in August", "William Faulkner", 1932));
        novels.add(new Novel("The Lion, The Witch and the Wardrobe", "C.S. Lewis", 1950));
        novels.add(new Novel("Lolita", "Vladimir Nabokov", 1955));
        novels.add(new Novel("Lord of the Flies", "William Golding", 1954));
        novels.add(new Novel("The Lord of the Rings", "J.R.R. Tolkien", 1954));
        novels.add(new Novel("Loving", "Henry Green", 1945));
        novels.add(new Novel("Lucky Jim", "Kingsley Amis", 1954));
        novels.add(new Novel("The Man Who Loved Children", "Christina Stead", 1940));
        novels.add(new Novel("Midnight's Children", "Salman Rushdie", 1981));
        novels.add(new Novel("Money", "Martin Amis", 1984));
        novels.add(new Novel("The Moviegoer", "Walker Percy", 1961));
        novels.add(new Novel("Mrs. Dalloway", "Virginia Woolf", 1925));
        novels.add(new Novel("Naked Lunch", "William Burroughs", 1959));
        novels.add(new Novel("Native Son", "Richard Wright", 1940));
        novels.add(new Novel("Neuromancer", "William Gibson", 1984));
        novels.add(new Novel("Never Let Me Go", "Kazuo Ishiguro", 2005));
        novels.add(new Novel("1984", "George Orwell", 1948));
        novels.add(new Novel("On the Road", "Jack Kerouac", 1957));
        novels.add(new Novel("One Flew Over the Cuckoo's Nest", "Ken Kesey", 1962));
        novels.add(new Novel("The Painted Bird", "Jerzy Kosinski", 1965));
        novels.add(new Novel("Pale Fire", "Vladimir Nabokov", 1962));
        novels.add(new Novel("A Passage to India", "E.M. Forster", 1924));
        novels.add(new Novel("Play It as It Lays", "Joan Didion", 1970));
        novels.add(new Novel("Portnoy's Complaint", "Philip Roth", 1969));
        novels.add(new Novel("Possession", "A.S. Byatt", 1990));
        novels.add(new Novel("The Power and the Glory", "Graham Greene", 1939));
        novels.add(new Novel("The Prime of Miss Jean Brodie", "Muriel Spark", 1961));
        novels.add(new Novel("Rabbit, Run", "John Updike", 1960));
        novels.add(new Novel("Ragtime", "E.L. Doctorow", 1975));
        novels.add(new Novel("The Recognitions", "William Gaddis", 1955));
        novels.add(new Novel("Red Harvest", "Dashiell Hammett", 1929));
        novels.add(new Novel("Revolutionary Road", "Richard Yates", 1961));
        novels.add(new Novel("The Sheltering Sky", "Paul Bowles", 1949));
        novels.add(new Novel("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
        novels.add(new Novel("Snow Crash", "Neal Stephenson", 1992));
        novels.add(new Novel("The Sot-Weed Factor", "John Barth", 1960));
        novels.add(new Novel("The Sound and the Fury", "William Faulkner", 1929));
        novels.add(new Novel("The Sportswriter", "Richard Ford", 1986));
        novels.add(new Novel("The Spy Who Came in from the Cold", "John le Carré", 1964));
        novels.add(new Novel("The Sun Also Rises", "Ernest Hemingway", 1926));
        novels.add(new Novel("Their Eyes Were Watching God", "Zora Neale Hurston", 1937));
        novels.add(new Novel("Things Fall Apart", "Chinua Achebe", 1959));
        novels.add(new Novel("To Kill a Mockingbird", "Harper Lee", 1960));
        novels.add(new Novel("To the Lighthouse", "Virginia Woolf", 1929));
        novels.add(new Novel("Tropic of Cancer", "Henry Miller", 1934));
        novels.add(new Novel("Ubik", "Philip K. Dick", 1969));
        novels.add(new Novel("Under the Net", "Iris Murdoch", 1954));
        novels.add(new Novel("Under the Volcano", "Malcolm Lowry", 1947));
        novels.add(new Novel("Watchmen", "Alan Moore and Dave Gibbons", 1986));
        novels.add(new Novel("White Noise", "Don DeLillo", 1985));
        novels.add(new Novel("White Teeth", "Zadie Smith", 2000));
        novels.add(new Novel("Wide Sargasso Sea", "Jean Rhys", 1966));

    }
}
