package org.cv.ocb.meta;

public enum GraphMeta {
    LINE_GRAPH(1, "折线图图"),
    BAR_GRAPH_CATEGORY(2, "类型柱状图");

    public Integer id;
    public String prompt;

    GraphMeta(Integer id, String prompt) {
        this.id = id;
        this.prompt = prompt;
    }
}
