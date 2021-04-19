import java.util.ArrayList;

public class Scheduler {

    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;


    private ArrayList<Assignment> solutionDynamic = new ArrayList<Assignment>();
    private ArrayList<Assignment> solutionGreedy;

    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {
        // Should be instantiated with an Assignment array
        // All the properties of this class should be initialized here
        if (assignmentArray.length == 0) {
            throw new IllegalArgumentException("Assignment Array is empty");
        } else {

            this.assignmentArray = assignmentArray;
            this.max = new Double[this.assignmentArray.length];
            this.C = new Integer[this.assignmentArray.length];
            this.calculateC();
        }


    }

    /**
     * @param index of the {@link Assignment}
     * @return Returns the index of the last compatible {@link Assignment},
     * returns -1 if there are no compatible assignments
     */
    private int binarySearch(int index) {
        String indexStartingTime = this.assignmentArray[index].getStartTime();
        if (index >= 1) {
            int first = 0;
            int last = index - 1;
            while (first <= last) {
                if (indexStartingTime.compareTo(this.assignmentArray[last].getFinishTime()) >= 0) {
                    return last;
                } else {
                    last--;
                }
            }
        }
        return -1;
    }


    /**
     * {@link #C} must be filled after calling this method
     */
    private void calculateC() {
        Integer[] finalC = new Integer[this.assignmentArray.length];
        for (int i = 0; i < this.assignmentArray.length; i++) {
            finalC[i] = binarySearch(i);
        }
        this.C = finalC;
    }

    /**
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleDynamic() {

        if(this.assignmentArray.length >= 1){
            for (int i = this.assignmentArray.length - 1; i >= 0; i--) {
                if(max[i] == null)
                    calculateMax(i);
            }
        }

        findSolutionDynamic(this.assignmentArray.length-1);


        return this.solutionDynamic;
    }

    /**
     * {@link #solutionDynamic} must be filled after calling this method
     */
    private void findSolutionDynamic(int i) {

        if(i != -1 ){
            System.out.println("findSolutionDynamic("+i+")");

            if(i==0){
                this.solutionDynamic.add(this.assignmentArray[i]);
                System.out.println("Adding " + this.assignmentArray[i] + " to the dynamic schedule");
            }else{

                if(this.max[i] > this.max[i-1]){
                    Assignment assignment = this.assignmentArray[i];
                    this.solutionDynamic.add(assignment);
                    System.out.println("Adding " + this.assignmentArray[i] + " to the dynamic schedule");
                    findSolutionDynamic(C[i]);
                }else{
                    findSolutionDynamic(i-1);
                }
            }
        }
    }

    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) {
        if (i < 0) {
            return 0.0;
        } else if (i == 0) {
            System.out.println("calculateMax(" + i + "): " + "Zero");
            if(assignmentArray.length>0){
                max[i] = assignmentArray[i].getWeight();
                return max[i];
            }else
                return 0.0;
        } else if (max[i] != null) {
            System.out.println("calculateMax(" + i + "): " + "Present");
            return max[i];
        } else {
            System.out.println("calculateMax(" + i + "): " + "Prepare");
            max[i] = Math.max(assignmentArray[i].getWeight() + calculateMax(C[i]),calculateMax(i - 1));
            return max[i];
        }


    }


    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {
        ArrayList<Assignment> scheduledArrayList = new ArrayList<Assignment>();
        int lastAddedAssignmentIndex = 0;
        for (int i = 0; i < this.assignmentArray.length; i++) {
            if (i == 0) {
                scheduledArrayList.add(this.assignmentArray[i]);
                System.out.println("Adding " + this.assignmentArray[i] + " to the greedy schedule");
                lastAddedAssignmentIndex = i;
            } else {
                if (this.assignmentArray[i].getStartTime().compareTo(this.assignmentArray[lastAddedAssignmentIndex].getFinishTime()) >= 0) {
                    scheduledArrayList.add(this.assignmentArray[i]);
                    System.out.println("Adding " + this.assignmentArray[i] + " to the greedy schedule");
                    lastAddedAssignmentIndex = i;
                }
            }
        }
        return scheduledArrayList;
    }
}
