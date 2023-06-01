package com.example.springstudy.board.repository;

import com.example.springstudy.board.model.BoardTypeCount;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardTypeCustomRepository {

    private final EntityManager entityManager;

    public List<BoardTypeCount> getBoardTypeCount() {

        String sql = " select bt.id, bt.board_name, bt.reg_date, bt.using_yn "
            + ", (select count(*) from board b where b.board_type_id = bt.id) as board_count "
            + " from board_type bt ";

//        List<BoardTypeCount> resultList = entityManager.createNativeQuery(sql).getResultList();

        Query nativeQuery = entityManager.createNativeQuery(sql);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<BoardTypeCount> resultList = jpaResultMapper.list(nativeQuery, BoardTypeCount.class);

        return resultList;
    }

}
