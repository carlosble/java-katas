import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import tdd.*;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReporterShould {

    class ReportWriterSpy implements ReportWriter {
        public UserReport writtenReport;

        @Override
        public void write(UserReport report) {
            this.writtenReport = report;
        }
    }

    @Test
    public void ask_writer_to_write_report() throws Exception {
        UserFinder finder = new UserFinder(null){
            @Override
            public List<User> findByProfile(String profile){
                return Arrays.asList(new User(0, "irrelevant", "irrelevant"));
            }
        };
        ReportWriterSpy writer = new ReportWriterSpy();
        Reporter reporter = new Reporter(finder, writer);

        reporter.generateReportBy("irrelevant");

        UserReport expectedReport = new UserReport(
                "irrelevant",
                Arrays.asList(new User(0, "irrelevant", "irrelevant")));
        assertThat(writer.writtenReport)
                .isEqualToComparingFieldByField(expectedReport);

    }

    @Test
    public void ask_writer_to_write_report_with_mockito() throws Exception {
        UserFinder finder = mock(UserFinder.class);
        ReportWriter writer = mock(ReportWriter.class);
        when(finder.findByProfile("irrelevant"))
                .thenReturn(Arrays.asList(
                        new User(0, "irrelevant", "irrelevant")));
        Reporter reporter = new Reporter(finder, writer);
        reporter.generateReportBy("irrelevant");

        UserReport expectedReport = new UserReport(
                "irrelevant",
                Arrays.asList(new User(0, "irrelevant", "irrelevant")));

        ArgumentCaptor<UserReport> captor = ArgumentCaptor.forClass(UserReport.class);
        verify(writer).write(captor.capture());
        assertThat(captor.getValue()).isEqualToComparingFieldByField(expectedReport);
    }
}
