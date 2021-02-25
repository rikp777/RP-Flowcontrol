package flowcontrol.production.service;

import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Optional<Line> getById(Long id){
        Line line = this.lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line", "Line not found", id));

        return Optional.of(line);
    }
}
