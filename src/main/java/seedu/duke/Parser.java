package seedu.duke;
import java.time.LocalDate;
import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger("ParserLogger");
    /**
     * Checks if the string is a number
     *
     * @param str The string that is to be defined as a number or sentence
     * @return true or false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Obtains the list of travel activities
     *
     * @param list List of travel activities.
     */
    public static void getList(String[] command, TravelActivityList list) throws OmniException{
        if (command.length == 1) {
            System.out.println("Here are the travel activities in your list:");
            list.listTravelActivities();
        } else {
            throw new OmniException("Do you mean the command list?");
        }
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void activityCommand(String line, TravelActivityList list) throws OmniException{
        String[] command = line.split(" ");
        String delimiter = command[0] + "| /date | /duration | /tag";
        String[] input = line.split(delimiter);
        if (input.length >= 5 && input[1].isEmpty()) {
            throw new OmniException("The description of accommodation cannot be empty!");
        } else if(input.length >= 5 && input[2].isEmpty()){
            throw new OmniException("The date cannot be empty!");
        } else if (input.length >= 5 && input[3].isEmpty()){
            throw new OmniException("The duration cannot be empty!");
        } else if (input.length >= 5 && input[4].isEmpty()){
            throw new OmniException("The tag cannot be empty!");
        } else if(input.length < 5) {
            throw new OmniException("Please check that your accommodation command is in this format: add DESCRIPTION " +
                                    "/date YYYY-MM-DD /duration DURATION /tag TAG");
        } else{
            if (command[0].equals("accommodation")) {
                Accommodation newActivity = new Accommodation(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new accommodation");
                System.out.println(newActivity);
            } else if (command[0].equals("food")) {
                Food newActivity = new Food(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new restaurant");
                System.out.println(newActivity);
            } else if (command[0].equals("landmark")) {
                Landmark newActivity = new Landmark(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new landmark");
                System.out.println(newActivity);
            }
        }
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void addCommand(String line, TravelActivityList list) throws OmniException{
        String[] command = line.split("add | /date | /duration | /tag ");
        //logger.log(Level.INFO, command[0] + " // " +  command[1]);
        if (command.length >= 5 && command[1].isEmpty()) {
            throw new OmniException("The description of add cannot be empty!");
        } else if(command.length >= 5 && command[2].isEmpty()){
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 5 && command[3].isEmpty()){
            throw new OmniException("The duration cannot be empty!");
        } else if(command.length < 5) {
            throw new OmniException("Please check that your add command is in this format: add DESCRIPTION " +
                    "/date YYYY-MM-DD /duration DURATION /tag TAG");
        } else{
            TravelActivity newActivity = new TravelActivity(command[1].trim(), LocalDate.parse(command[2]),
                    command[3].trim(), command[4].trim());
            list.addTravelActivity(newActivity);
            System.out.println("I added a new travel activity");
            System.out.println(newActivity);
        }
    }

    /**
     * Handles the case where the delete command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void deleteCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.removeTravelActivity(listNumber);

        } else {
            throw new OmniException("Please specify which activity to delete");
        }
    }

    /**
     *  Handles the case where the find command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2
     */
    public static void findCommand(String[] command, TravelActivityList list) throws OmniException{
        if (command.length == 2) {
            String keyword = command[1];
            list.searchKeyword(keyword);
        } else {
            throw new OmniException("Please specify an appropriate keyword you want to find!");
        }
    }

    /**
     * Handles the case where the check command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void checkCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.checkTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to check");
        }
    }

    /**
     * Handles the case where the uncheck command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void uncheckCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.uncheckTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to uncheck");
        }
    }

    /**
     * Handles the case where the tag command is given as input
     *
     * @param line array of input string
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void tagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("tag |-");
        if (command.length == 3 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            String tag = command[2];
            list.tagActivity(listNumber, tag);
        } else if (command.length == 2) {
            throw new OmniException("Please specify a tag name");
        } else {
            throw new OmniException("Please specify which task to tag");
        }
    }

    /**
     * Handles the case where the untag command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void removeTagCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeTag(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove tag");
        }
    }

    public static void updateCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("update | /date | /duration | /tag");
        if (command.length >= 5 && (command[1].isEmpty() || !isNumeric(command[1]))) {
            throw new OmniException("The update index cannot be empty or non numerical!");
        } else if (command.length >= 5 && command[2].isEmpty()) {
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 5 && command[3].isEmpty()) {
            throw new OmniException("The duration cannot be empty!");
        } else if (command.length >= 5 && command[4].isEmpty()) {
            throw new OmniException("The tag cannot be empty!");
        } else if (command.length < 5) {
            throw new OmniException("Please check that your update command is in this format: update INDEX " +
                    "/date YYYY-MM-DD /duration DURATION");
        } else {
            list.updateTravelActivity(Integer.parseInt(command[1]), LocalDate.parse(command[2]), command[3],
                    command[4]);
        }
    }

    public static void findTagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtag");
        if (command.length < 1) {
            throw new OmniException("Please check that your update command is in this format: findtag <task tag>");
        } else {
            list.findTag(command[1].trim());
        }
    }

}
