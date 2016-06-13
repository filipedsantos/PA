package model.states;

import model.data.DataGame;

public class AwaitBeginning extends StateAdapter{

    public AwaitBeginning(DataGame dataGame) {
        super(dataGame);
    }

    @Override
    public IStates start(){
        return new AwaitOption(getDataGame());
    }
}
