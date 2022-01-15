import string
import redis
import json
import smtplib
from email.message import EmailMessage


def send_email(content: string, from_where: string, to_where: string, subject: string):
    em = EmailMessage()
    em['Subject'] = subject
    em['From'] = from_where
    em['To'] = to_where
    em.set_content(content)

    smtp = smtplib.SMTP('smtp-server', 1025)
    smtp.send_message(em)
    smtp.quit()


if __name__ == '__main__':
    r = redis.Redis(host='queue-service', port=6379, db=0)
    p = r.pubsub()
    p.subscribe('transactional-emails')

    while True:
        message = p.get_message()
        if message is not None:
            if message['data'] is not 1:
                try:
                    messageObj = json.loads(message['data'])
                    send_email(
                        messageObj['content'],
                        messageObj['from'],
                        messageObj['to'],
                        messageObj['subject']
                    )
                    print('Email sent successfully!')
                except Exception:
                    print('Failed to send the email.')
