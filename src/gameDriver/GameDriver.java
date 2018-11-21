package gameDriver;

import gameModeling.Game;
import nonAIAgents.Agent;
import nonAIAgents.Aggressive;
import nonAIAgents.Passive;

public class GameDriver {


    private Agent agent0;
    private Agent agent1;
    private Game game;
    private int turnNumber;

    public GameDriver(PlayersTypes player0, PlayersTypes player1) {
        this.agent0 = this.setAgents(player0);
        this.agent1 = this.setAgents(player1);
        this.game = new Game();
        this.turnNumber = 0;
    }

    private Agent setAgents(PlayersTypes playerType) {
        if (playerType.equals(PlayersTypes.PASSIVE)) {
            return new Passive();
        } else if (playerType.equals(PlayersTypes.HUMAN)) {
            return new Aggressive();
        }
        return null;
        // to be changed remove null :D
    }


    public Game playTurn() {

        if (turnNumber == 0) {
            this.game.addTroops(0);
            ((Passive) agent0).performActions(game, turnNumber);
            turnNumber = 1;
        } else {
            this.game.addTroops(1);
            ((Passive) agent1).performActions(game, turnNumber);
            turnNumber = 0;
        }

        this.printGraph();

        return game;
    }

    private void printGraph() {
        for (int i = 1; i < this.game.getGraph().adjacencyList.length; i++) {
            System.out.print(i + " Owner: " + this.game.getGraph().getOwner(i) + ": ");
            System.out.println(this.game.getGraph().getTroopsInVertex(i));
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}