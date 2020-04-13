//package com.example.book.config;
//
//import com.example.book.batch.CsvJobListener;
//import com.example.book.domain.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 批处理配置
// * zhangyd
// * 2020/4/13 11:30
// */
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Bean
//    public ItemReader<User> reader() throws Exception {
//        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource("c:/user.txt"));
//        //文件数据和模型类对应映射
//        reader.setLineMapper(new DefaultLineMapper<User>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[]{"name", "password", "phone_num"});
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
//                setTargetType(User.class);
//            }});
//        }});
//        return reader;
//    }
//
//    @Bean
//    public ItemWriter<User> writer(DataSource dataSource) {
//        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        String sql = "insert into user(name,password,phone_num) values(:name,:password,:phone_num)";
//        writer.setSql(sql);
//        writer.setDataSource(dataSource);
//        return writer;
//    }
//
//    @Bean
//    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager platformTransactionManager) throws Exception {
//        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setDataSource(dataSource);
//        jobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
//        jobRepositoryFactoryBean.setDatabaseType("mysql");
//        return jobRepositoryFactoryBean.getObject();
//    }
//
//    @Bean
//    public SimpleJobLauncher simpleJobLauncher(DataSource dataSource, PlatformTransactionManager platformTransactionManager) throws Exception {
//        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//        jobLauncher.setJobRepository(jobRepository(dataSource, platformTransactionManager));
//        return jobLauncher;
//    }
//
//    @Bean
//    public Job importJob(JobBuilderFactory jobs, Step s1) {
//        return jobs.get("importJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(s1)
//                .end()
//                .listener(csvJobListener())
//                .build();
//    }
//
//    @Bean
//    public Step step1(StepBuilderFactory stepBuilderFactory,
//                      ItemReader<User> reader, ItemWriter<User> writer,
//                      ItemProcessor<User, User> processor) {
//
//        return stepBuilderFactory.get("step1")
//                .<User, User>chunk(65000)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//
//    @Bean
//    public CsvJobListener csvJobListener() {
//        return new CsvJobListener();
//    }
//
//}
//
