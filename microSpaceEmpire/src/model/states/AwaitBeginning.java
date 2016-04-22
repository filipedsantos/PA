package model.states;


public class AwaitBeginning extends StateAdapter{

    public AwaitBeginning() {
         
    }
    
    @Override
    public IStates start(){
        return new AwaitOption();
    }
}
