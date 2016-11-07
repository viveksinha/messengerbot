package co.aurasphere.facebot.autoreply;

import co.aurasphere.facebot.bean.FaceBotNetworkAwareBean;
import co.aurasphere.facebot.event.FaceBotEvent;
import co.aurasphere.facebot.model.incoming.MessageEnvelope;
import co.aurasphere.facebot.model.outcoming.FaceBotResponse;
import lombok.Getter;
import org.apache.http.entity.StringEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface that represents an automatic reply to an event. AutoReply are
 * used in conjuction with {@link FaceBotEvent} in order to handle an Facebook's
 * Messenger Platform callback automatically, using the delegation design
 * pattern.
 *
 * @author Donato
 * @date 31/lug/2016
 */
public abstract class AutoReply extends FaceBotNetworkAwareBean {
    @Getter
    protected List<FaceBotResponse> faceBotResponseList = new ArrayList<>();

    /**
     * Method which defines the reply flow.
     *
     * @param envelope the current callback message
     */
    public void reply(MessageEnvelope envelope) {
        createResponse(envelope);
        for (FaceBotResponse response : faceBotResponseList) {
            if (response != null) {
                // If the response is valid, replies to it.
                if (validate(response)) {
                    StringEntity jsonResponse = toJson(response);
                    postMessage(jsonResponse);
                }
            }
        }
    }

    /**
     * Method which defines the response to send back as a response to the
     * current message.
     *
     * @param envelope the current message.
     * @return a {@link FaceBotResponse} which contains the response to the
     * current message.
     */
    public abstract void createResponse(MessageEnvelope envelope);

}
