package MODEL;
public class PageRequest {
    private int page;
    private int pageSize; 

    public PageRequest(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize <= 0) pageSize = 10;
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1) page = 1;
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }
}