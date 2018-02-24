package com.siwoo.document_application.domain;
import com.siwoo.document_application.domain.converter.BooleanToYNConverter;
import com.siwoo.document_application.exception.domain.DocumentTagsException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="sb_document")
@Getter @Setter @ToString(exclude = "member")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SequenceGenerator(
        name = "spring_document_generator",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "document_seq")
public class Document {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spring_document_generator")
    @Column(name="document_id")
    private Long id;

    private String title;

    //tags used for searching purpose
    //Only three tag is available for One Document
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="sb_document_tags",
            joinColumns = @JoinColumn(name="document_id"))
    @Column(name="tags")
    private List<String> tags = new ArrayList<>();

    public void setTags(List<String> tags){
        validateTags(tags);
        this.tags = tags;
    }

    /* validate tag list size. and if not valid, throw exception */
    private void validateTags(List<String> tags){
        /*null should be ok but size must be below 4*/
        if(tags != null && tags.size() > 3){
            throw new DocumentTagsException("Document[tags.size:"+tags.size()+"] not valid","error.invalid.document.tags.size");
        }
    }

    /* Add tag */
    public boolean addTag(String tag){
        boolean added = false;
        try {
            validateTags(this.tags);
            if ( tags.size() < 3 && StringUtils.hasText(tag)) {
                this.tags.add(tag);
                added = true;
            }
        }catch (DocumentTagsException e){
            added = false;
        }
        return added;
    }


    /*
        if secret is true
        the document cannot share with other member
    */
    @Convert(converter = BooleanToYNConverter.class)
    private boolean secret = false;

    private String text;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="member_id")
    private Member member;

    @CreationTimestamp
    private LocalDateTime postDate;

    /*
        The number of views for document
    */
    private int hit = 0;

    @Transient
    private Double ratings = null;

    public Document(String title, String text, boolean secret, Member member, int hit,List<String> tags,LocalDateTime postDate) {
        this.title = title;
        this.tags = tags;
        this.secret = secret;
        this.text = text;
        this.member = member;
        this.postDate = postDate;
        this.hit = hit;
        this.ratings = ratings;
    }
}
