// Generated by view binder compiler. Do not edit!
package com.ssafy.memo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ssafy.memo.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMemoEditBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button cancelButton;

  @NonNull
  public final EditText descriptionInput;

  @NonNull
  public final Button saveButton;

  @NonNull
  public final EditText toDoListInput;

  private ActivityMemoEditBinding(@NonNull LinearLayout rootView, @NonNull Button cancelButton,
      @NonNull EditText descriptionInput, @NonNull Button saveButton,
      @NonNull EditText toDoListInput) {
    this.rootView = rootView;
    this.cancelButton = cancelButton;
    this.descriptionInput = descriptionInput;
    this.saveButton = saveButton;
    this.toDoListInput = toDoListInput;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMemoEditBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMemoEditBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_memo_edit, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMemoEditBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cancelButton;
      Button cancelButton = ViewBindings.findChildViewById(rootView, id);
      if (cancelButton == null) {
        break missingId;
      }

      id = R.id.descriptionInput;
      EditText descriptionInput = ViewBindings.findChildViewById(rootView, id);
      if (descriptionInput == null) {
        break missingId;
      }

      id = R.id.saveButton;
      Button saveButton = ViewBindings.findChildViewById(rootView, id);
      if (saveButton == null) {
        break missingId;
      }

      id = R.id.toDoListInput;
      EditText toDoListInput = ViewBindings.findChildViewById(rootView, id);
      if (toDoListInput == null) {
        break missingId;
      }

      return new ActivityMemoEditBinding((LinearLayout) rootView, cancelButton, descriptionInput,
          saveButton, toDoListInput);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
