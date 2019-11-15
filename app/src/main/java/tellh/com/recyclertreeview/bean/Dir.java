package tellh.com.recyclertreeview.bean;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by tlh on 2016/10/1 :)
 */

public class Dir implements LayoutItemType {
    public String dirName;
    public String imgUrl;

    public Dir(String dirName, String img) {
        this.dirName = dirName;
        this.imgUrl = img;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dir;
    }
}
