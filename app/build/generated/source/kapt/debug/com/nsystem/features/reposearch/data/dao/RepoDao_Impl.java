package com.nsystem.features.reposearch.data.dao;

import android.database.Cursor;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nsystem.features.reposearch.data.model.RepoEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RepoDao_Impl implements RepoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RepoEntity> __insertionAdapterOfRepoEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearRepo;

  public RepoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepoEntity = new EntityInsertionAdapter<RepoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `repo` (`id`,`full_name`,`description`,`owner_name`,`star_count`,`fork_count`,`open_issue_count`,`repository_url`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RepoEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getFullName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFullName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getOwnerName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getOwnerName());
        }
        stmt.bindLong(5, value.getStarCount());
        stmt.bindLong(6, value.getForkCount());
        stmt.bindLong(7, value.getOpenIssueCount());
        if (value.getRepositoryUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getRepositoryUrl());
        }
      }
    };
    this.__preparedStmtOfClearRepo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM repo";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<RepoEntity> repos,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRepoEntity.insert(repos);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearRepo(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearRepo.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearRepo.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public PagingSource<Integer, RepoEntity> searchRepo(final String queryString) {
    final String _sql = "SELECT * FROM repo WHERE full_name LIKE ? OR description LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (queryString == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, queryString);
    }
    _argIndex = 2;
    if (queryString == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, queryString);
    }
    return new LimitOffsetPagingSource<RepoEntity>(_statement, __db, "repo") {
      @Override
      protected List<RepoEntity> convertRows(Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(cursor, "full_name");
        final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
        final int _cursorIndexOfOwnerName = CursorUtil.getColumnIndexOrThrow(cursor, "owner_name");
        final int _cursorIndexOfStarCount = CursorUtil.getColumnIndexOrThrow(cursor, "star_count");
        final int _cursorIndexOfForkCount = CursorUtil.getColumnIndexOrThrow(cursor, "fork_count");
        final int _cursorIndexOfOpenIssueCount = CursorUtil.getColumnIndexOrThrow(cursor, "open_issue_count");
        final int _cursorIndexOfRepositoryUrl = CursorUtil.getColumnIndexOrThrow(cursor, "repository_url");
        final List<RepoEntity> _result = new ArrayList<RepoEntity>(cursor.getCount());
        while(cursor.moveToNext()) {
          final RepoEntity _item;
          final long _tmpId;
          _tmpId = cursor.getLong(_cursorIndexOfId);
          final String _tmpFullName;
          if (cursor.isNull(_cursorIndexOfFullName)) {
            _tmpFullName = null;
          } else {
            _tmpFullName = cursor.getString(_cursorIndexOfFullName);
          }
          final String _tmpDescription;
          if (cursor.isNull(_cursorIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = cursor.getString(_cursorIndexOfDescription);
          }
          final String _tmpOwnerName;
          if (cursor.isNull(_cursorIndexOfOwnerName)) {
            _tmpOwnerName = null;
          } else {
            _tmpOwnerName = cursor.getString(_cursorIndexOfOwnerName);
          }
          final int _tmpStarCount;
          _tmpStarCount = cursor.getInt(_cursorIndexOfStarCount);
          final int _tmpForkCount;
          _tmpForkCount = cursor.getInt(_cursorIndexOfForkCount);
          final int _tmpOpenIssueCount;
          _tmpOpenIssueCount = cursor.getInt(_cursorIndexOfOpenIssueCount);
          final String _tmpRepositoryUrl;
          if (cursor.isNull(_cursorIndexOfRepositoryUrl)) {
            _tmpRepositoryUrl = null;
          } else {
            _tmpRepositoryUrl = cursor.getString(_cursorIndexOfRepositoryUrl);
          }
          _item = new RepoEntity(_tmpId,_tmpFullName,_tmpDescription,_tmpOwnerName,_tmpStarCount,_tmpForkCount,_tmpOpenIssueCount,_tmpRepositoryUrl);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
