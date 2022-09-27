package stocktradingsimulator.stocks.earnings.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.stocks.earnings.entity.EarningsReport;
import stocktradingsimulator.stocks.earnings.repository.EarningsRepository;

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

    public List<EarningsReport> findAllEarningsByDate(String date){
        return earningsRepository.findAll().stream()
                .filter(earnings -> earnings.getDateOfRelease().equalsIgnoreCase(date))
                .collect(Collectors.toList());
    }

    public void saveEarningsReport(EarningsReport earningsReport){
        earningsRepository.save(earningsReport);
    }
}
