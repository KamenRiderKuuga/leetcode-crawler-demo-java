package com.hanabi.leetcodecrawler.pojo;

/**
 * 从LeetCode返回的判题结果实体
 */
public class LeetCodeJudgementResult {

    private int status_code;
    private String lang;
    private boolean run_success;
    private String runtime_error;
    private String full_runtime_error;
    private String compile_error;
    private String full_compile_error;
    private String status_runtime;
    private long memory;
    private int question_id;
    private int elapsed_time;
    private String compare_result;
    private String code_output;
    private String std_output;
    private String last_testcase;
    private String expected_output;
    private long task_finish_time;
    private String task_name;
    private boolean finished;
    private String status_msg;
    private String state;
    private boolean fast_submit;
    private int total_correct;
    private int total_testcases;
    private String submission_id;
    private String runtime_percentile;
    private String status_memory;
    private String memory_percentile;
    private String pretty_lang;
    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
    public int getStatus_code() {
        return status_code;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getLang() {
        return lang;
    }

    public void setRun_success(boolean run_success) {
        this.run_success = run_success;
    }
    public boolean getRun_success() {
        return run_success;
    }

    public void setRuntime_error(String runtime_error) {
        this.runtime_error = runtime_error;
    }
    public String getRuntime_error() {
        return runtime_error;
    }

    public void setFull_runtime_error(String full_runtime_error) {
        this.full_runtime_error = full_runtime_error;
    }
    public String getFull_runtime_error() {
        return full_runtime_error;
    }

    public void setCompile_error(String compile_error) {
        this.compile_error = compile_error;
    }
    public String getCompile_error() {
        return compile_error;
    }

    public void setFull_compile_error(String full_compile_error) {
        this.full_compile_error = full_compile_error;
    }
    public String getFull_compile_error() {
        return full_compile_error;
    }

    public void setStatus_runtime(String status_runtime) {
        this.status_runtime = status_runtime;
    }
    public String getStatus_runtime() {
        return status_runtime;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }
    public long getMemory() {
        return memory;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
    public int getQuestion_id() {
        return question_id;
    }

    public void setElapsed_time(int elapsed_time) {
        this.elapsed_time = elapsed_time;
    }
    public int getElapsed_time() {
        return elapsed_time;
    }

    public void setCompare_result(String compare_result) {
        this.compare_result = compare_result;
    }
    public String getCompare_result() {
        return compare_result;
    }

    public void setCode_output(String code_output) {
        this.code_output = code_output;
    }
    public String getCode_output() {
        return code_output;
    }

    public void setStd_output(String std_output) {
        this.std_output = std_output;
    }
    public String getStd_output() {
        return std_output;
    }

    public void setLast_testcase(String last_testcase) {
        this.last_testcase = last_testcase;
    }
    public String getLast_testcase() {
        return last_testcase;
    }

    public void setExpected_output(String expected_output) {
        this.expected_output = expected_output;
    }
    public String getExpected_output() {
        return expected_output;
    }

    public void setTask_finish_time(long task_finish_time) {
        this.task_finish_time = task_finish_time;
    }
    public long getTask_finish_time() {
        return task_finish_time;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
    public String getTask_name() {
        return task_name;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public boolean getFinished() {
        return finished;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }
    public String getStatus_msg() {
        return status_msg;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setFast_submit(boolean fast_submit) {
        this.fast_submit = fast_submit;
    }
    public boolean getFast_submit() {
        return fast_submit;
    }

    public void setTotal_correct(int total_correct) {
        this.total_correct = total_correct;
    }
    public int getTotal_correct() {
        return total_correct;
    }

    public void setTotal_testcases(int total_testcases) {
        this.total_testcases = total_testcases;
    }
    public int getTotal_testcases() {
        return total_testcases;
    }

    public void setSubmission_id(String submission_id) {
        this.submission_id = submission_id;
    }
    public String getSubmission_id() {
        return submission_id;
    }

    public void setRuntime_percentile(String runtime_percentile) {
        this.runtime_percentile = runtime_percentile;
    }
    public String getRuntime_percentile() {
        return runtime_percentile;
    }

    public void setStatus_memory(String status_memory) {
        this.status_memory = status_memory;
    }
    public String getStatus_memory() {
        return status_memory;
    }

    public void setMemory_percentile(String memory_percentile) {
        this.memory_percentile = memory_percentile;
    }
    public String getMemory_percentile() {
        return memory_percentile;
    }

    public void setPretty_lang(String pretty_lang) {
        this.pretty_lang = pretty_lang;
    }
    public String getPretty_lang() {
        return pretty_lang;
    }

}
