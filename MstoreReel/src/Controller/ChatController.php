<?php

namespace App\Controller;

use App\Entity\Chat;
use Doctrine\DBAL\Query\QueryException;
use Pusher\Pusher;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ChatController extends AbstractController
{
    /**
     * @Route("/chat", name="chat")
     */
    public function index(): Response
    {
        $user = $this->getUser();
        $idChannel = $user ->getRole()[0] . '_channel';



        $query = $this->getDoctrine()->getManager()->createQuery("SELECT c FROM App\Entity\Chat c WHERE c.channel = :channel")->setMaxResults(1);
        $query->setParameter('channel', $idChannel);
        $chat = $query->getOneOrNullResult();

        // no result or non unique result

        if($chat == null){
            $chat= new Chat();
            $chat->setChannel($idChannel);
            $chat->setDiscussion([]);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($chat);
            $entityManager->flush();
        }


        //$discussion=$chat->getDiscussion();

        $discussion = $chat->getDiscussion();

        return $this->render('chat/index.html.twig', [
            'discussion' => $discussion,
            'pusherKey' => $this->getParameter('pusherKey'),
            'pusherCluster' => $this->getParameter('pusherCluster'),
        ]); 

        
    }


    /**
     * @Route("/chatt", name="demo_chat", methods={"POST"})
     */
    public function indexPusher(Request $request, Pusher $pusher): Response
    {

        //$chat_message = json_decode($request->getContent(), true)['chat_message'];
        //$chat_message['message'];
        $user = $this->getUser();
        $idChannel = $user ->getRoles()[0] . '_channel';
        $idEvent = $user ->getRoles()[0] . '_event';
        $data = $request->get('message');


        $query = $this->getDoctrine()->getManager()->createQuery("SELECT c FROM App\Entity\Chat c WHERE c.channel = :channel")->setMaxResults(1);
        $query->setParameter('channel', $idChannel);

            // default action is always to return a Document
            $chat = $query->getOneOrNullResult();

            // no result or non unique result
            if($chat == null){
                $chat= new Chat();
                $chat->setChannel($idChannel);
                $chat->setDiscussion([]);
            }

            
            $discussion=$chat->getDiscussion();
            array_push($discussion,$data);
            $chat->setDiscussion($discussion);


            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($chat);
            $entityManager->flush();


            if($pusher->trigger($idChannel, $idEvent, $data)) {

                

                echo 'success';
            
            } else {
            
                echo 'error';
            
            }


        return new Response();

    }



}
