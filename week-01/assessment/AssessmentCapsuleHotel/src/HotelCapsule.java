import java.util.Scanner;

public class HotelCapsule {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        int roomBooked = RoomNumPrompt(console);
        String[] hotelRooms = BuildRoomArray(roomBooked);

        boolean mainLoop = true;

        while(mainLoop) {

            int choice = MainMenu(console);

            // Switch Statement that decides with function to be run depending on the output
            // from the mainMenu function.
            switch(choice) {

                case 1:
                    checkIn(console,hotelRooms);
                    break;
                case 2:
                    checkOut(console,hotelRooms);
                    break;
                case 3:
                    ViewGuests(console,hotelRooms);
                    break;
                case 4:
                    Quit(console);
                    break;
            }

        }


    }


    // Ask users for the total room number
    public static int RoomNumPrompt(Scanner console) {

        boolean roomNumPromptLoop = true;
        int roomBooked = 0;

        while(roomNumPromptLoop) {

            System.out.print("Welcome to Railview Hotel\n");
            System.out.println("========================");
            System.out.println("Please Enter the number of rooms");
            String totalRoom = console.nextLine();
            int totalRoomInt = Integer.parseInt(totalRoom);

            if(totalRoomInt > 0) {

                roomBooked = totalRoomInt;
                roomNumPromptLoop = false;
            } else {
                System.out.println("Not a valid input please try again\n");
            }
        }
        return roomBooked;
    }


    // Build array based on dimeansion given by user
    public static String[] BuildRoomArray(int roomBooked) {

        String[] hotelRooms = new String[roomBooked];
        return hotelRooms;
    }


    // Function that prompt user to select from 1 - 4
    // If Input is valid the function loops over until a valid answer is given
    public static int MainMenu(Scanner console) {

        int choiceInt = 0;
        boolean mainMenuLoop = true;
        while (mainMenuLoop) {
            System.out.println("Main Menu [1-4]:\n1.Check In\n2.Check Out\n3.View Guests\n4.Exit\n");
            String choice = console.nextLine();
            choiceInt = Integer.parseInt(choice);

            if(choiceInt == 1 || choiceInt == 2 || choiceInt == 3 || choiceInt == 4) {
                mainMenuLoop = false;
            } else {
                System.out.println("Not a valid choice\n===============================\n");
            }
        }

        return choiceInt;
    }


    // Prompts user for a name and room number
    // If room number is occupied the functions loops over using a while loop
    public static void checkIn(Scanner console, String[] hotelRooms) {

        System.out.println("Guest Name:");
        String guestName = console.nextLine();
        boolean occupied = true;

        while(occupied) {

            System.out.println("Please enter room number");
            String roomNum = console.nextLine();
            int roomNumInt = Integer.parseInt(roomNum);

            if (roomNumInt - 1 > 0 && roomNumInt - 1 < hotelRooms.length && hotelRooms[roomNumInt - 1] == null) {
                hotelRooms[roomNumInt - 1] = guestName;
                System.out.printf("%s has been checked in to room %d\n=============================\n", guestName, roomNumInt);
                occupied = false;
            } else {
                System.out.println("That room is occupied/not valid\n=========================\n");
            }
        }
    }


    // Check in Function ask user for room number
    // If room number is valid check out the user and set it equal to null
    public static void checkOut(Scanner console, String[] hotelsRooms) {

        System.out.println("Guess Checkout:\n=====================\n");

        boolean roomNotOccupied = true;
        while(roomNotOccupied) {
            System.out.println("Please enter the room Number");
            String roomNum = console.nextLine();
            int roomNumInt = Integer.parseInt(roomNum);

            if(roomNumInt - 1 > 0 && hotelsRooms[roomNumInt-1] != null && roomNumInt - 1 < hotelsRooms.length) {
                System.out.printf("%s has been checked out of room %d\n==========================\n",hotelsRooms[roomNumInt-1],roomNumInt);
                hotelsRooms[roomNumInt-1] = null;
                roomNotOccupied = false;
            } else {
                System.out.println("Not a valid Room\n==============================\n");
            }
        }
    }


    public static void ViewGuests(Scanner console, String[] hotelRooms) {

        boolean promptLoop = true;

        while(promptLoop) {

            System.out.println("Please enter a room number:");
            String roomNumber = console.nextLine();
            int roomNumberInt = Integer.parseInt(roomNumber);

            if(roomNumberInt - 1 >= 0 && roomNumberInt - 1 <= hotelRooms.length) {

                for(int rooms = roomNumberInt - 6; rooms < roomNumberInt + 6; rooms++) {

                    if(rooms > 0 && rooms < hotelRooms.length) {

                        if(hotelRooms[rooms] == null) {
                            System.out.printf("%d:%s\n",rooms + 1,"Unoccupied");
                        } else {
                            System.out.printf("%d:%s\n", rooms + 1, hotelRooms[rooms]);
                        }
                    }
                }
                promptLoop = false;
            } else {
                System.out.println("Not a valid room number\n===================================\n");
            }
        }
    }


    //Function that will cause the program to close should the user want to.
    public static void Quit(Scanner console) {

        System.out.println("Do you want to Quit [y/n]? (you will lose all data)");
        String answer = console.nextLine();
        if(answer.equalsIgnoreCase("y")) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }
}

