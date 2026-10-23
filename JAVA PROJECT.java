
import java.util.ArrayList;
import java.util.Scanner;

class SavingsGoal {
    int id;
    String goalName;
    double targetAmount;
    double savedAmount;

    SavingsGoal(int id, String goalName, double targetAmount) {
        this.id = id;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.savedAmount = 0;
    }

    void addSavings(double amount) {
        savedAmount += amount;
    }

    void recordExpense(double amount) {
        savedAmount -= amount;
    }

    void displayProgress() {
        double percentage = (savedAmount / targetAmount) * 100;

        System.out.println("\nGoal ID       : " + id);
        System.out.println("Goal Name     : " + goalName);
        System.out.println("Target Amount : Rs. " + targetAmount);
        System.out.println("Saved Amount  : Rs. " + savedAmount);
        System.out.printf("Progress      : %.2f%%\n", percentage);

        if (savedAmount >= targetAmount) {
            System.out.println("Status        : Goal Achieved!");
        } else {
            System.out.println("Amount Needed : Rs. "
                    + (targetAmount - savedAmount));
        }
    }
}

public class SavingsGoalPlanningSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<SavingsGoal> goals = new ArrayList<>();
    static int nextId = 1;

    static SavingsGoal findGoal(int id) {
        for (SavingsGoal goal : goals) {
            if (goal.id == id) {
                return goal;
            }
        }
        return null;
    }

    static void createGoal() {
        System.out.print("Enter goal name: ");
        String name = sc.nextLine();

        System.out.print("Enter target amount: Rs. ");
        double target = sc.nextDouble();
        sc.nextLine();

        if (target <= 0) {
            System.out.println("Target must be greater than zero.");
            return;
        }

        goals.add(new SavingsGoal(nextId++, name, target));
        System.out.println("Savings goal created successfully!");
    }

    static void addSavings() {
        System.out.print("Enter goal ID: ");
        int id = sc.nextInt();

        SavingsGoal goal = findGoal(id);

        if (goal == null) {
            System.out.println("Goal not found!");
            return;
        }

        System.out.print("Enter savings amount: Rs. ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else {
            goal.addSavings(amount);
            System.out.println("Savings added successfully!");
        }
    }

    static void recordExpense() {
        System.out.print("Enter goal ID: ");
        int id = sc.nextInt();

        SavingsGoal goal = findGoal(id);

        if (goal == null) {
            System.out.println("Goal not found!");
            return;
        }

        System.out.print("Enter expense amount: Rs. ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
        } else if (amount > goal.savedAmount) {
            System.out.println("Insufficient savings balance!");
        } else {
            goal.recordExpense(amount);
            System.out.println("Expense recorded successfully!");
        }
    }

    static void viewGoals() {
        if (goals.isEmpty()) {
            System.out.println("No savings goals available.");
            return;
        }

        for (SavingsGoal goal : goals) {
            goal.displayProgress();
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== SAVINGS GOAL PLANNING SYSTEM =====");
            System.out.println("1. Create Savings Goal");
            System.out.println("2. Add Savings");
            System.out.println("3. Record Expense");
            System.out.println("4. View Savings Progress");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createGoal();
                    break;

                case 2:
                    addSavings();
                    break;

                case 3:
                    recordExpense();
                    break;

                case 4:
                    viewGoals();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
