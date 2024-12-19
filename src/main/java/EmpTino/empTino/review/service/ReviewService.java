package EmpTino.empTino.review.service;

import EmpTino.empTino.review.domain.ReviewDAO;
import EmpTino.empTino.review.repository.ReviewDAORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewDAORepository reviewDAORepository;

    public ReviewService(ReviewDAORepository reviewDAORepository){
        this.reviewDAORepository = reviewDAORepository;
    }

    public void createReview(ReviewDAO reviewDAO) {
        reviewDAORepository.save(reviewDAO);
    }

    public List<ReviewDAO> findAllReviews() {
        return reviewDAORepository.findAll();
    }

}
