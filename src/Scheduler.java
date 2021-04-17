import java.util.ArrayList;

public class Scheduler {

    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;
    private ArrayList<Assignment> solutionDynamic;
    private ArrayList<Assignment> solutionGreedy;

    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {
        // Should be instantiated with an Assignment array
        // All the properties of this class should be initialized here
        if(assignmentArray.length == 0){
            throw new IllegalArgumentException("Assignment Array is empty");
        }else{
            this.assignmentArray = assignmentArray;
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
        if(index >= 1 ){
            int first = 0;
            int last = index - 1;
            while(first <= last){
                if(indexStartingTime.compareTo(this.assignmentArray[last].getFinishTime())>= 0){
                    return last;
                }else{
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
//        for(int j = 0 ; j<this.C.length ; j++){
//            System.out.println(C[j]);
//        }
    }


    /**
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleDynamic() {
        return null;
    }

    /**
     * {@link #solutionDynamic} must be filled after calling this method
     */
    private void findSolutionDynamic(int i) {

    }

    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) {
        return null;
    }

    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {
        return null;
    }
}
