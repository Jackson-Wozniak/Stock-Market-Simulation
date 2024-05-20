package org.api.tradinggame.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Leaderboard {

    private int ranking;
    private String username;
    private double totalProfits;
}
