package com.example.lap10715.notesappcleanarchitecture.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ViewSwitcher;

import com.example.lap10715.notesappcleanarchitecture.R;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.DeleteNoteUseCase;
import com.example.lap10715.notesappcleanarchitecture.domain.usecase.GetNoteUseCase;
import com.example.lap10715.notesappcleanarchitecture.presentation.contract.ShowNotesContract;
import com.example.lap10715.notesappcleanarchitecture.presentation.model.NotePresentationModel;
import com.example.lap10715.notesappcleanarchitecture.presentation.presenter.IPresenter;
import com.example.lap10715.notesappcleanarchitecture.presentation.presenter.ShowNotesPresenter;

public class ShowNotesActivity extends BaseActivity implements ShowNotesContract.View , MyAdapter.NoteObserver{

    private ShowNotesPresenter mPresenter;
    private MyAdapter mMyAdapter;
    private RecyclerView mRvListNote;
    private ViewSwitcher mViewSwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewSwitcher = findViewById(R.id.view_switcher);

        createPresenter();
        mPresenter.attach(this);
        bindPresenterToView(mPresenter);

        setUpRecyclerView();

        FloatingActionButton btnStartAddNote = findViewById(R.id.fbtn_add);
        btnStartAddNote.setOnClickListener(v->{
            startActivity(new Intent(this, AddNoteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.populateNotesFromPrefs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detach();
    }

    private void setUpRecyclerView() {
        mMyAdapter = new MyAdapter();
        mMyAdapter.setNoteObserver(this);

        mRvListNote = findViewById(R.id.rc_list_notes);
        mRvListNote.setHasFixedSize(false);
        mRvListNote.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvListNote.setAdapter(mMyAdapter);

        // swipe item.
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mMyAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRvListNote);

    }

    private void createPresenter() {
        mPresenter = new ShowNotesPresenter(mUseCaseHandler,
                new GetNoteUseCase(mPreferenceNoteApi),
                new DeleteNoteUseCase(mPreferenceNoteApi));
    }

    @Override
    public void clearNoteList() {
        mMyAdapter.clearList();
    }

    @Override
    public void showNewNote(NotePresentationModel noteModel) {
        mMyAdapter.addNote(noteModel);
    }

    @Override
    public void showList() {
        mViewSwitcher.setDisplayedChild(1);
    }

    @Override
    public void showEmptyPlaceholder() {
        mViewSwitcher.setDisplayedChild(0);
    }

    @Override
    public void onNoteListChange(int oldItemCount, int newItemCount) {
        mPresenter.showListOrEmptyPlaceholder(oldItemCount, newItemCount);
    }

    @Override
    public void onItemDelete(NotePresentationModel noteModel) {
        mPresenter.deleteNoteFromMemory(noteModel);
    }
}
