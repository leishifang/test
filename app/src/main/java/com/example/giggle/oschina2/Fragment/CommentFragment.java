package com.example.giggle.oschina2.Fragment;

import com.example.giggle.oschina2.R;
import com.example.giggle.oschina2.adpter.BaseListAdapter;
import com.example.giggle.oschina2.adpter.CommentAdapter;
import com.example.giggle.oschina2.api.OSChinaAPI;
import com.example.giggle.oschina2.base.BaseListFragment;
import com.example.giggle.oschina2.bean.Comment;
import com.example.giggle.oschina2.bean.CommentList;
import com.example.giggle.oschina2.bean.EntityList;
import com.example.giggle.oschina2.util.XmlUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by leiShifang on 2016/1/2 22:43.
 */
public class CommentFragment extends BaseListFragment<Comment> {

    public static final String BUNDLE_KEY_ID = "BUNDLE_KEY_ID";
    public static final String BUNDLE_KEY_CATALOG = "BUNDLE_KEY_CATALOG";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listview_pulltorefresh;
    }

    @Override
    protected EntityList<Comment> parserList(InputStream is) {
        CommentList commentList = XmlUtils.toBean(CommentList.class, is);
        return commentList;
    }

    @Override
    protected void executeOnLoadDataSuccess(List<Comment> data) {
        super.executeOnLoadDataSuccess(data);
    }

    @Override
    protected void sendRequestData() {
        OSChinaAPI.getCommentList(mCatalog,
                getActivity().getIntent().getIntExtra(BUNDLE_KEY_ID, 0), mCurrentPage, mResponseHandler);
    }

    @Override
    protected BaseListAdapter<Comment> getListAdapter() {
        return new CommentAdapter();
    }
}
