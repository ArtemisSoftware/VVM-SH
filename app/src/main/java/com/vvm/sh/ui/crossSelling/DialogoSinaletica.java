package com.vvm.sh.ui.crossSelling;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoSinaleticaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.opcoes.BaseDaggerDialogFragment;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerDialogFragment;

public class DialogoSinaletica extends BaseDaggerDialogFragment {



    private DialogoSinaleticaBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private CrossSellingViewModel viewModel;


    //private NoteDialogListener listener;

    private static final String ARG_ID_POKEMON = "idPokemon";
    private static final String ARG_NAME = "name";
    private static final String ARG_NOTE = "note";


    public DialogoSinaletica() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoSinaletica newInstance() {
        DialogoSinaletica frag = new DialogoSinaletica();
//        Bundle args = new Bundle();
//        args.putString(ARG_ID_POKEMON, idPokemon);
//        args.putString(ARG_NAME, name);
//        frag.setArguments(args);
        return frag;
    }



    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(CrossSellingViewModel.class);

        binding = (DialogoSinaleticaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        builder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //--saveNote();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                terminarDialogo();
            }
        });
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_sinaletica;
    }

    @Override
    protected int obterTitulo() {
        return R.string.sinaletica;
    }



    //
//    public static NoteDialogFragment newInstance(String name, Note note) {
//        NoteDialogFragment frag = new NoteDialogFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_NAME, name);
//        args.putParcelable(ARG_NOTE, note);
//        frag.setArguments(args);
//        return frag;
//    }
//

//
//    private void initDialog() {
//
//        if (getArguments() != null) {
//            binding.txtName.setText(getArguments().getString(ARG_NAME));
//
//            if(getArguments().containsKey(ARG_NOTE) == true){
//
//                binding.setNote(getArguments().getParcelable(ARG_NOTE));
//            }
//        }
//    }
//
//
//    /**
//     * Call this method to send the data back to the parent fragment
//     */
//    public void saveNote() {
//
//        Note note;
//
//        if(getArguments().containsKey(ARG_NOTE) == true){
//            note = getArguments().getParcelable(ARG_NOTE);
//
//            note.setDescription(binding.txtInpNote.getText().toString());
//            note.setRegisterDate(new Date());
//        }
//        else{
//            note = new Note(Integer.parseInt(getArguments().getString(ARG_ID_POKEMON)), binding.txtInpNote.getText().toString(), new Date());
//        }
//
//
//        listener.saveNote(note);
//        dismiss();
//    }
//
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        if (context instanceof NoteDialogListener) {
//            listener = (NoteDialogListener) context;
//        }
//        else {
//            throw new RuntimeException(context.toString() + " must implement NoteDialogListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }
//
//
//    public interface NoteDialogListener {
//
//        void saveNote(Note note);
//    }

}
