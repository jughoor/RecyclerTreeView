package tellh.com.recyclertreeview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tellh.com.recyclertreeview.bean.Dir;
import tellh.com.recyclertreeview.bean.File;
import tellh.com.recyclertreeview.viewbinder.DirectoryNodeBinder;
import tellh.com.recyclertreeview.viewbinder.FileNodeBinder;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TreeViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        initData();
    }

    private void initData() {
        List<TreeNode> nodes = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            TreeNode<Dir> app = new TreeNode<>(new Dir("app", "http://i.imgur.com/DvpvklR.png"));
            nodes.add(app);
            app.addChild(
                    new TreeNode<>(new Dir("manifests", "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png"))
                            .addChild(new TreeNode<>(new Dir("AndroidManifest.xml", "http://i.imgur.com/DvpvklR.png")))
                            .addChild(new TreeNode<>(new Dir("AndroidManifest.xml", "http://i.imgur.com/DvpvklR.png")))
                            .addChild(new TreeNode<>(new Dir("AndroidManifest.xml", "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png")))
            );
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {}
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree)
                        .start();
            }
        });
        rv.setAdapter(adapter);
    }

}
