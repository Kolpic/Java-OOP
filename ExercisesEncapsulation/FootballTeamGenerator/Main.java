package FootballTeamGenerator;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Map<String,Team> teams = new LinkedHashMap<>();

        while (!command.equals("END")) {

            String[] commandArr = command.split(";");

            String activity = commandArr[0];
            String teamName = commandArr[1];

            try {

                switch (activity) {


                    case "Team":

                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;

                    case "Add":

                        String playerToAdd = commandArr[2];
                        int endurance = Integer.parseInt(commandArr[3]);
                        int sprint = Integer.parseInt(commandArr[4]);
                        int dribble = Integer.parseInt(commandArr[5]);
                        int passing = Integer.parseInt(commandArr[6]);
                        int shooting = Integer.parseInt(commandArr[7]);

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {

                            Player player = new Player(playerToAdd, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }

                        break;

                    case "Remove":

                        String playerToRemove = commandArr[2];

                        teams.get(teamName).removePlayer(playerToRemove);
                        break;

                    case "Rating":

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                        }
                        break;
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            command = scanner.nextLine();
        }
    }
}
