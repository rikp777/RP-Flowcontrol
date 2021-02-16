package flowcontrol.production.service;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    @Autowired
    private final LineRepository lineRepository;

    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public List<Line> getAll(){
        return this.lineRepository.findAll();
    }

    public Line get(String id){
        return this.lineRepository.findById(id).orElse(null);
    }
}
