package com.codekl.roadbudee.Service;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ObservableBoolean extends BaseObservable implements Parcelable, Serializable {
    static final long serialVersionUID = 1L;
    private boolean mValue;

    /**
     * Creates an ObservableBoolean with the given initial value.
     *
     * @param value the initial value for the ObservableBoolean
     */
    public ObservableBoolean(boolean value) {
        mValue = value;
    }
    /**
     * Creates an ObservableBoolean with the initial value of <code>false</code>.
     */
    public ObservableBoolean() {
    }
    /**
     * @return the stored value.
     */
    public boolean get() {
        return mValue;
    }
    /**
     * Set the stored value.
     */
    public void set(boolean value) {
        if (value != mValue) {
            mValue = value;
            notifyChange();
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mValue ? 1 : 0);
    }
    public static final Parcelable.Creator<ObservableBoolean> CREATOR
            = new Parcelable.Creator<ObservableBoolean>() {
        @Override
        public ObservableBoolean createFromParcel(Parcel source) {
            return new ObservableBoolean(source.readInt() == 1);
        }
        @Override
        public ObservableBoolean[] newArray(int size) {
            return new ObservableBoolean[size];
        }
    };
}
