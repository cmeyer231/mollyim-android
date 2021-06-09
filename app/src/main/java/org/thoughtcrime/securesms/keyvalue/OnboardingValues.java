package org.thoughtcrime.securesms.keyvalue;

import android.content.Context;

import androidx.annotation.NonNull;

import org.thoughtcrime.securesms.dependencies.ApplicationDependencies;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.util.Collections;
import java.util.List;

public final class OnboardingValues extends SignalStoreValues {

  private static final String SHOW_NEW_GROUP      = "onboarding.new_group";
  private static final String SHOW_INVITE_FRIENDS = "onboarding.invite_friends";
  private static final String SHOW_SMS            = "onboarding.sms";
  private static final String SHOW_APPEARANCE     = "onboarding.appearance";

  OnboardingValues(@NonNull KeyValueStore store) {
    super(store);
  }

  @Override
  void onFirstEverAppLaunch() {
    putBoolean(SHOW_NEW_GROUP, true);
    putBoolean(SHOW_INVITE_FRIENDS, true);
    putBoolean(SHOW_SMS, true);
    putBoolean(SHOW_APPEARANCE, true);
  }

  @Override
  @NonNull List<String> getKeysToIncludeInBackup() {
    return Collections.emptyList();
  }

  public void clearAll() {
    setShowNewGroup(false);
    setShowInviteFriends(false);
    setShowEnableApkUpdate(false);
    setShowAppearance(false);
  }

  public boolean hasOnboarding(@NonNull Context context) {
    return shouldShowNewGroup()      ||
           shouldShowInviteFriends() ||
           shouldShowEnableApkUpdate(context) ||
           shouldShowAppearance();
  }

  public void setShowNewGroup(boolean value) {
    putBoolean(SHOW_NEW_GROUP, value);
  }

  public boolean shouldShowNewGroup() {
    return getBoolean(SHOW_NEW_GROUP, false);
  }

  public void setShowInviteFriends(boolean value) {
    putBoolean(SHOW_INVITE_FRIENDS, value);
  }

  public boolean shouldShowInviteFriends() {
    return getBoolean(SHOW_INVITE_FRIENDS, false);
  }

  public void setShowEnableApkUpdate(boolean value) {
    TextSecurePreferences.setUpdateApkShowOnboardingEnabled(ApplicationDependencies.getApplication(), value);
  }

  public boolean shouldShowEnableApkUpdate(@NonNull Context context) {
    return !TextSecurePreferences.isUpdateApkEnabled(context) && TextSecurePreferences.isUpdateApkShowOnboardingEnabled(context);
  }

  public void setShowAppearance(boolean value) {
    putBoolean(SHOW_APPEARANCE, value);
  }

  public boolean shouldShowAppearance() {
    return getBoolean(SHOW_APPEARANCE, false);
  }
}
