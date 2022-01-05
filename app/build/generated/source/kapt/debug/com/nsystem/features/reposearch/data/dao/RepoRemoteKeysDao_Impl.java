package com.nsystem.features.reposearch.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nsystem.features.reposearch.data.model.RepoRemoteKeysEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RepoRemoteKeysDao_Impl implements RepoRemoteKeysDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RepoRemoteKeysEntity> __insertionAdapterOfRepoRemoteKeysEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearRepoRemoteKeys;

  public RepoRemoteKeysDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepoRemoteKeysEntity = new EntityInsertionAdapter<RepoRemoteKeysEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `repo_remote_keys` (`repo_id`,`prev_key`,`next_key`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RepoRemoteKeysEntity value) {
        stmt.bindLong(1, value.getRepoId());
        if (value.getPrevKey() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getPrevKey());
        }
        if (value.getNextKey() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getNextKey());
        }
      }
    };
    this.__preparedStmtOfClearRepoRemoteKeys = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM repo_remote_keys";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<RepoRemoteKeysEntity> repoRemoteKeysEntities,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRepoRemoteKeysEntity.insert(repoRemoteKeysEntities);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearRepoRemoteKeys(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearRepoRemoteKeys.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearRepoRemoteKeys.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getRepoRemoteKeys(final long repoId,
      final Continuation<? super RepoRemoteKeysEntity> continuation) {
    final String _sql = "SELECT * FROM repo_remote_keys WHERE repo_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, repoId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RepoRemoteKeysEntity>() {
      @Override
      public RepoRemoteKeysEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRepoId = CursorUtil.getColumnIndexOrThrow(_cursor, "repo_id");
          final int _cursorIndexOfPrevKey = CursorUtil.getColumnIndexOrThrow(_cursor, "prev_key");
          final int _cursorIndexOfNextKey = CursorUtil.getColumnIndexOrThrow(_cursor, "next_key");
          final RepoRemoteKeysEntity _result;
          if(_cursor.moveToFirst()) {
            final long _tmpRepoId;
            _tmpRepoId = _cursor.getLong(_cursorIndexOfRepoId);
            final Integer _tmpPrevKey;
            if (_cursor.isNull(_cursorIndexOfPrevKey)) {
              _tmpPrevKey = null;
            } else {
              _tmpPrevKey = _cursor.getInt(_cursorIndexOfPrevKey);
            }
            final Integer _tmpNextKey;
            if (_cursor.isNull(_cursorIndexOfNextKey)) {
              _tmpNextKey = null;
            } else {
              _tmpNextKey = _cursor.getInt(_cursorIndexOfNextKey);
            }
            _result = new RepoRemoteKeysEntity(_tmpRepoId,_tmpPrevKey,_tmpNextKey);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
