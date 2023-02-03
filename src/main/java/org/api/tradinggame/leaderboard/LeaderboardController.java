package org.api.tradinggame.leaderboard;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/leaderboard")
@AllArgsConstructor
public class LeaderboardController {

    @Autowired
    private final ProcessLeaderboard processLeaderboard;

    @RequestMapping
    public List<Leaderboard> getLeaderboard() {
        return processLeaderboard.topTenAccounts();
    }

    @RequestMapping(value = "/top3")
    public List<Leaderboard> getTopThree() {
        return processLeaderboard.topThreeAccounts();
    }

    @RequestMapping(value = "/ranking/{username}")
    public Leaderboard getAccountRanking(@PathVariable String username) {
        return processLeaderboard.findAccountRanking(username);
    }
}
