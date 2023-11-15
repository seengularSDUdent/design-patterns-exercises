import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
	    NutritionPlanDirector director = new NutritionPlanDirector();
	    
	    //Maintenance Plan 
		
		NutritionPlanBuilder maintenanceBuilder = new MaintenanceNutritionPlanBuilder();
		director.setBuilder(maintenanceBuilder);
		
		director.createNutritionPlan();
		NutritionPlan maintenanceObject = maintenanceBuilder.build();
		
		System.out.println(maintenanceObject);
		System.out.println(maintenanceObject.getFitnessGoal());
		
		//Weight Loss Plan
		
		NutritionPlanBuilder weightLossBuilder = new WeightLossNutritionPlanBuilder();
		director.setBuilder(weightLossBuilder);
		
		director.createNutritionPlan();
		NutritionPlan weightLossObject = weightLossBuilder.build();
		
		System.out.println(weightLossObject);
		System.out.println(weightLossObject.getFitnessGoal());
		
		//Weight Gain Plan
		
		NutritionPlanBuilder weightGainBuilder = new WeightGainNutritionPlanBuilder();
		director.setBuilder(weightGainBuilder);
		
		director.createNutritionPlan();
		NutritionPlan weightGainObject = weightGainBuilder.build();
		
		System.out.println(weightGainObject);
		System.out.println(weightGainObject.getFitnessGoal());
		
		//Simple Plan
		
		NutritionPlanBuilder simplePlan = new NutritionPlanBuilder();
		director.setBuilder(simplePlan);
		
		director.createNutritionPlan();
		NutritionPlan simplePlanObject = simplePlan.build();
		
		System.out.println(simplePlanObject);
		System.out.println(simplePlanObject.getFitnessGoal());
	}
}

class NutritionPlan {

    int dailyCaloricIntake;
    int[] macroNutrientRatios = new int[3];
    List<String> mealPlans = new ArrayList<String>();
    String fitnessGoal;
    List<String> dietaryRestrictions = new ArrayList<String>();
    
    String getFitnessGoal(){
        return fitnessGoal;
    }
}

class NutritionPlanDirector{
    
    private NutritionPlanBuilder currentBuilder;
    
    NutritionPlanDirector(){
    }
    
    NutritionPlanDirector(NutritionPlanBuilder setValue){
        this.currentBuilder = setValue;
    }
    
    void setBuilder(NutritionPlanBuilder setValue){
        this.currentBuilder = setValue;
    }
    
    void createNutritionPlan(){
        if(currentBuilder instanceof MaintenanceNutritionPlanBuilder){
            System.out.println("It's for maintenance plan!");
            currentBuilder.reset();
            currentBuilder.setFitnessGoal("");
        }
        else if(currentBuilder instanceof WeightLossNutritionPlanBuilder){
            System.out.println("It's for weight loss plan!");
            currentBuilder.reset();
            currentBuilder.setFitnessGoal("");
        }
        else if(currentBuilder instanceof WeightGainNutritionPlanBuilder){
            System.out.println("It's for weight gain plan!");
            currentBuilder.reset();
            currentBuilder.setFitnessGoal("");
        }
        else{
            System.out.println("It's for simple plan!");
            currentBuilder.reset();
        }
    }
    
}

class NutritionPlanBuilder {
    
    private NutritionPlan inProcess;
    
    NutritionPlanBuilder(){
        this.reset();
    }
    
    void reset(){
        this.inProcess = new NutritionPlan();
    }
    
    void setCaloricIntake(int setValue){
        this.inProcess.dailyCaloricIntake = setValue;
    }
    
    void setMacroNutrientRatios(int setValue0, int setValue1, int setValue2){
        this.inProcess.macroNutrientRatios[0] = setValue0;
        this.inProcess.macroNutrientRatios[1] = setValue1;
        this.inProcess.macroNutrientRatios[2] = setValue2;
    }
    
    void setMealPlans(List<String> setValue){
        this.inProcess.mealPlans = setValue;
    }
    
    void setFitnessGoal(String setValue){
        this.inProcess.fitnessGoal = setValue;
    }
    
    void setDietaryRestrictions(List<String> setValue){
        this.inProcess.dietaryRestrictions = setValue;
    }
    
    NutritionPlan build(){
        return this.inProcess;
    }
}

class WeightLossNutritionPlanBuilder extends NutritionPlanBuilder{
    
    private NutritionPlan inProcess;
    
    WeightLossNutritionPlanBuilder(){
        this.reset();
    }
    
    void reset(){
        this.inProcess = new NutritionPlan();
    }
    
    @Override
    void setFitnessGoal(String setValue){
        this.inProcess.fitnessGoal = "Weight loss";
    }
    
    @Override
    NutritionPlan build(){
        return this.inProcess;
    }
}

class WeightGainNutritionPlanBuilder extends NutritionPlanBuilder{
    
    private NutritionPlan inProcess;
    
    WeightGainNutritionPlanBuilder(){
        this.reset();
    }
    
    void reset(){
        this.inProcess = new NutritionPlan();
    }
    
    @Override 
    void setFitnessGoal(String setValue){
        this.inProcess.fitnessGoal = "Weight gain";
    }
    
    @Override
    NutritionPlan build(){
        return this.inProcess;
    }
}

class MaintenanceNutritionPlanBuilder extends NutritionPlanBuilder{
    
    private NutritionPlan inProcess;
    
    MaintenanceNutritionPlanBuilder(){
        this.reset();
    }
    
    void reset(){
        this.inProcess = new NutritionPlan();
    }
    
    @Override
    void setFitnessGoal(String setValue){
        this.inProcess.fitnessGoal = "Maintenance";
    }
    
    @Override
    NutritionPlan build(){
        return this.inProcess;
    }
}