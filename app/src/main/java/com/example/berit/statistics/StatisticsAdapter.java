package com.example.berit.statistics;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class StatisticsAdapter extends CursorAdapter {

    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public StatisticsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view=layoutInflater.inflate(R.layout.statistics,parent,false);
        parentViewGroup = parent;
        return view;
    }

    // this can be called several times by the system!!!
    // first pass - initial draw, get measurements
    // second pass - final draw
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

//        TextView textViewName =(TextView) view.findViewById(R.id.name);
//
//        Person person = uow.personRepo.cursorToEntity(cursor);
//        textViewName.setText(person.getFirstLastName());
//        displayContactsView(view, context, person);
    }
//
//
//    private void displayStatisticsView(View view, Context context, Person person) {
//        // get the statisticsListView
//        LinearLayout statListView = (LinearLayout) view.findViewById(R.id.contactsListView);
//
//        // if this gets called multiple times, first clean all up
//        // otherwise you will add same childs several times
//        contactsListView.removeAllViews();
//
//        for (Contact contact :
//                uow.contactRepo.getForPerson(person.getId())) {
//
//            // load the xml structure of your row
//            View child = layoutInflater.inflate(R.layout.contact_details,parentViewGroup,false);
//
//            TextView textViewContactValue =(TextView) child.findViewById(R.id.contactValue);
//            TextView textViewContactType =(TextView) child.findViewById(R.id.contactType);
//
//            textViewContactValue.setText(contact.getValue());
//            textViewContactType.setText(uow.contactTypeRepo.getById(contact.getContactTypeId()).getValue());
//
//            contactsListView.addView(child);
//        }
//    }
}