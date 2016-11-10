package education.wondersgroup.com.mvvpdemo.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangwentao on 16/10/10.
 * Description :
 * Version :
 */
public class Student implements Parcelable {
    private String name;
    private String pwd;

    public Student(){
        super();
    }

    protected Student(Parcel in) {
        name = in.readString();
        pwd = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
