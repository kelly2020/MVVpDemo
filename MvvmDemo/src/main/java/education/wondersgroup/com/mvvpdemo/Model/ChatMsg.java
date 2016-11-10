package education.wondersgroup.com.mvvpdemo.Model;

/**
 * Created by zhangwentao on 16/9/14.
 * Description :聊天
 * Version :1.0
 */
public class ChatMsg {
    private String content;
    private boolean type;//true from  false coming


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
