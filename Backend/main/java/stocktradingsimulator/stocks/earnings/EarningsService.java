package stocktradingsimulator.stocks.earnings;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EarningsService {

    @Autowired
    private final EarningsRepository earningsRepository;

    public List<EarningsReport> findAllEarningsReports(){
        return earningsRepository.findAll();
    }

    public List<EarningsReport> findAllEarningsReportsByTicker(String ticker){
        return earningsRepository.findAll().stream()
                .filter(earnings -> earnings.getStock().getTicker().equalsIgnoreCase(ticker))
                .collect(Collectors.toList());
    }

    public void saveEarningsReport(EarningsReport earningsReport){
        earningsRepository.save(earningsReport);
    }
}
