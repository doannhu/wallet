package retail_banking_deposit.com.wallet.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/interest-post-batch")
public class InterestPostBatchJobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job updateAccountBalanceJob;

    @PostMapping("/run-job")
    public ResponseEntity<String> runBatchJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(updateAccountBalanceJob, params);
            return ResponseEntity.ok("Batch job has been invoked successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while running the batch job", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
