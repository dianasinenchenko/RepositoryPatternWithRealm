package com.devitis.repositorypatternwithrealm.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Diana on 03.04.2019.
 */

public abstract class MainAdapter<T, OT, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> list;
    private WeakReference<Observer<OT>> observerWeakReference;
    private WeakReference<Context> context;
    protected PublishSubject<T> publishSubject = PublishSubject.create();

    public MainAdapter(final Observer<OT> observer, final Context context) {
        this.observerWeakReference = new WeakReference<>(observer);
        this.list = new ArrayList<>();
        this.context = new WeakReference<Context>(context);
    }

    public MainAdapter(final Context context) {

        this.list = new ArrayList<>();
        this.context = new WeakReference<Context>(context);
    }

    public void subscribeToAdapter(@NonNull DisposableObserver<T> observer) {
        publishSubject.subscribe(observer);
    }

    public void unSubscribeToAdapter() {
        publishSubject.onComplete();
    }

    protected void onNext(OT value) {
        final Observer<OT> observer = observerWeakReference.get();
        if (observer == null) {
            return;
        }
        observer.onNext(value);
    }

    protected boolean hasObserver() {
        return observerWeakReference.get() != null;
    }

    protected Observer<OT> getObserver() {
        return observerWeakReference.get();
    }

    protected Context getContext() {
        return context.get();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return getViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @LayoutRes
    protected abstract int getLayout();

    @NonNull
    protected abstract VH getViewHolder(View view);

    public void add(T item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void add(List<T> newItems) {
        if (newItems.isEmpty()) {
            return;
        }
        int position = list.size() - 1;
        list.addAll(newItems);
        notifyItemRangeInserted(position, newItems.size());
    }

    public void add(int position, T item) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void add(int position, List<T> newItems) {
        list.addAll(position, newItems);
        notifyItemRangeInserted(position, newItems.size());
    }

    public void add(List<Integer> positions, List<T> newItems) {
        for (int i = 0; i < newItems.size(); i++) {
            int positionToInsert = positions.get(i);

            list.add(positionToInsert, newItems.get(i));
            notifyItemInserted(positionToInsert);
        }
    }

    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(int position, List<T> newItems) {
        list.removeAll(newItems);
        notifyItemRangeRemoved(position, newItems.size());
    }

    public void remove(List<Integer> positions, List<T> newItems) {
        for (int i = newItems.size() - 1; i >= 0; i--) {
            list.remove(newItems.get(i));
            notifyItemRemoved(positions.get(i));
        }
    }

    public void removeAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public abstract List<T> getItems();
}
